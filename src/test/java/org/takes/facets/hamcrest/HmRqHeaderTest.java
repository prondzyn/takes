/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.takes.facets.hamcrest;

import java.util.Arrays;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.takes.rq.RqFake;

/**
 * Test case for {@link org.takes.facets.hamcrest.HmRqHeader}.
 * @author Eugene Kondrashev (eugene.kondrashev@gmail.com)
 * @version $Id$
 * @since 0.23.3
 */
public final class HmRqHeaderTest {

    /**
     * HmRqHeader can test header available.
     * @throws Exception If some problem inside
     */
    @Test
    public void testsHeaderAvailable() throws Exception {
        MatcherAssert.assertThat(
            new RqFake(
                Arrays.asList(
                    "GET /f?a=3&b-6",
                    "Host: example.com",
                    "Accept: text/xml",
                    "Accept: text/html"
                ),
                ""
            ),
            new HmRqHeader(Matchers.hasEntry("accept", "text/xml"))
        );
    }

    /**
     * HmRqHeader can test header not available.
     * @throws Exception If some problem inside
     */
    @Test
    public void testsHeaderNotAvailable() throws Exception {
        MatcherAssert.assertThat(
            new RqFake(
                Arrays.asList(
                    "GET /f?a=3",
                    "Host: www.example.com",
                    "Accept: text/json"
                ),
                ""
            ),
            new HmRqHeader(
                Matchers.not(
                    Matchers.hasEntry("host", "fake.org")
                )
            )
        );
    }
}
