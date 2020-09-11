package com.my.qs.nettydemo.serialize;

public interface Serializer {

    byte JSON_SERIALIZE = 1;

    Serializer DEFAULT = new JsonSerializer();

    byte getSerializerAlgorithm();


    byte[] serialize(Object object);


    <T> T deserialize(Class<T> clazz, byte[] bytes);

}