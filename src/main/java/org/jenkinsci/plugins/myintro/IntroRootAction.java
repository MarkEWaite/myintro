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

import hudson.Extension;
import hudson.model.RootAction;

/**
 * <p>IntroRootAction class.</p>
 *
 * @author MarkE
 * @version $Id: $Id
 */
@Extension
public class IntroRootAction implements RootAction {

    /**
     * {@inheritDoc}
     *
     * Return icon file name.
     */
    @Override
    public String getIconFileName() {
        return "clipboard.png";
    }

    /**
     * {@inheritDoc}
     *
     * Name to display in the root menu.
     */
    @Override
    public String getDisplayName() {
        return "Intro Root Action";
    }

    /**
     * {@inheritDoc}
     *
     * URL to open when the root action is clicked.
     */
    @Override
    public String getUrlName() {
        return "https://jenkins.io/";
    }
    
}
