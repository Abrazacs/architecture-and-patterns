package ru.sergeysemenov.handler;

import org.reflections.Reflections;
import ru.sergeysemenov.ResponseSerializer;
import ru.sergeysemenov.services.FileService;
import ru.sergeysemenov.services.SocketService;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MethodHandlerFactory {

    public static MethodHandler create(SocketService socketService, ResponseSerializer responseSerializer, FileService fileService) {
        PutMethodHandler putHandler = new PutMethodHandler(null, socketService, responseSerializer);
        PostMethodHandler postHandler = new PostMethodHandler(putHandler, socketService, responseSerializer);
        return new GetMethodHandler(postHandler, socketService, responseSerializer, fileService);
    }


    public static MethodHandler createAnnotated (SocketService socketService, ResponseSerializer responseSerializer, FileService fileService) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Reflections reflections = new Reflections("ru.sergeysemenov.handler");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Handler.class);
        List<Class<?>> sortedListOfClasses = classes.stream().sorted(Comparator.comparingInt(MethodHandlerFactory::getOrderFromAnnotation)).toList();
        List<MethodHandler> handlers = new ArrayList<>();
        MethodHandlerImpl next = null;
        MethodHandler handler;
        for (int i = sortedListOfClasses.size()-1; i >= 0 ; i--) {
            if (!sortedListOfClasses.get(i).equals(Class.forName("ru.sergeysemenov.handler.GetMethodHandler"))){
                handler = getMethodHandlerFromClass(next, socketService, responseSerializer, sortedListOfClasses.get(i));
                handlers.add(handler);
            }else {
                return getMethodHandlerFromClass(next, socketService, responseSerializer,sortedListOfClasses.get(i),fileService);
            }
            next = (MethodHandlerImpl) handler;
        }
        return handlers.get(handlers.size()-1);
    }


    private static MethodHandler getMethodHandlerFromClass(MethodHandlerImpl handler, SocketService socketService, ResponseSerializer responseSerializer, Class<?> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return (MethodHandler) clazz.getConstructor(new Class[]{MethodHandlerImpl.class, SocketService.class, ResponseSerializer.class}).
                newInstance(handler, socketService, responseSerializer);
    }

    private static MethodHandler getMethodHandlerFromClass(MethodHandlerImpl handler, SocketService socketService, ResponseSerializer responseSerializer, Class<?> clazz, FileService fileService) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return (MethodHandler) clazz.getConstructor(new Class[]{MethodHandlerImpl.class, SocketService.class, ResponseSerializer.class, FileService.class}).
                newInstance(handler, socketService, responseSerializer, fileService);
    }

    private static int getOrderFromAnnotation(Class<?> clazz) {
        return clazz.getAnnotation(Handler.class).order();
    }

}
