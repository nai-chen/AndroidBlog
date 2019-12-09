package com.blog.demo.tool;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class CommonExampleTest {

    @Test
    public void testCallArgumentInstance() {
        File file = PowerMockito.mock(File.class);
        CommonExample commonExample = new CommonExample();
        PowerMockito.when(file.exists()).thenReturn(true);
        Assert.assertTrue(commonExample.callArgumentInstance(file));
    }

    @Test
    @PrepareForTest(CommonExample.class)
    public void callCallArgumentInstance2() throws Exception {
        File file = PowerMockito.mock(File.class);
        CommonExample commonExample = new CommonExample();
        PowerMockito.whenNew(File.class).withArguments("aaa").thenReturn(file);
        PowerMockito.when(file.exists()).thenReturn(true);

        Assert.assertTrue(commonExample.callArgumentInstance("aaa"));
        File newFile = Mockito.mock(File.class);
        newFile.exists();
        Mockito.verify(newFile).exists();
    }

}
