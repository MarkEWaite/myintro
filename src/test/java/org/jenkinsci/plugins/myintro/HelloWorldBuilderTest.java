/*
 * The MIT License
 *
 * Copyright 2017 MarkE.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jenkinsci.plugins.myintro;

import hudson.FilePath;
import hudson.Launcher;
import hudson.model.Run;
import hudson.model.TaskListener;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MarkE
 */
public class HelloWorldBuilderTest {
    
    public HelloWorldBuilderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSetSleepTime() {
        final String name = "a name";
        HelloWorldBuilder builder = new HelloWorldBuilder(name);
        long sleepTime = 1234L;
        builder.setSleepTime(sleepTime);
        assertThat(builder.getSleepTime(), is(sleepTime));
    }

    @Test
    public void testGetSleepTime() {
        final String name = "a name";
        HelloWorldBuilder builder = new HelloWorldBuilder(name);
        long sleepTime = 1234L;
        builder.setSleepTime(sleepTime);
        assertThat(builder.getSleepTime(), is(sleepTime));
    }

    // @Test
    public void testGetName() {
        System.out.println("getName");
        HelloWorldBuilder instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    // @Test
    public void testPerform() throws Exception {
        System.out.println("perform");
        Run build = null;
        FilePath workspace = null;
        Launcher launcher = null;
        TaskListener listener = null;
        HelloWorldBuilder instance = null;
        instance.perform(build, workspace, launcher, listener);
        fail("The test case is a prototype.");
    }

    // @Test
    public void testGetDescriptor() {
        System.out.println("getDescriptor");
        HelloWorldBuilder instance = null;
        HelloWorldBuilder.DescriptorImpl expResult = null;
        HelloWorldBuilder.DescriptorImpl result = instance.getDescriptor();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
