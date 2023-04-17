package com.zw.cloud.netty.web.redis;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.zw.cloud.netty.web.entity.poem.Poem;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class KryoTest {

    @Test
    public void testKryo() throws FileNotFoundException {
        Poem poem = new Poem();
        poem.setTitle("test");
        Kryo kryo = new Kryo();
        kryo.register(Poem.class);
        Output output = new Output(new FileOutputStream("test.txt"));
        kryo.writeObject(output, poem);
        output.close();
        Input input = new Input(new FileInputStream("test.txt"));
        Poem poem1 = kryo.readObject(input, Poem.class);
        input.close();
        assertTrue(poem.getTitle().equals(poem1.getTitle()));
    }

}
