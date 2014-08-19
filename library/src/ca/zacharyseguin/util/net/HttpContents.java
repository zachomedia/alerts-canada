/*
The MIT License (MIT)

Copyright (c) 2014 Zachary Seguin

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package ca.zacharyseguin.util.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.URL;

/**
 * Class to get the contents of a file requested via web.
 *
 * @author      Zachary Seguin (contact@zacharyseguin.ca)
 * @version     1.0
 * @since       1.0
 */
public class HttpContents
{
    public static String getURLContents(URL url)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String temp = "";
            String contents = "";

            while (true)
            {
                int c = reader.read();

                if (c == -1)
                {
                    break;
                }// End of if

                contents += (char)c;
            }// End of while

            return contents;
        }// End of try
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }// End of catch
    }// End of getURLContents method
}// End of class
