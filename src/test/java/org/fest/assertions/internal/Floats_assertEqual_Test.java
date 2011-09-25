/*
 * Created on Oct 24, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.error.ShouldBeEqual.shouldBeEqual;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.mockito.Mockito.*;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.test.ExpectedException;
import org.junit.*;

/**
 * Tests for <code>{@link Floats#assertEqual(AssertionInfo, Float, float)}</code>.
 *
 * @author Alex Ruiz
 */
public class Floats_assertEqual_Test {

  @Rule public ExpectedException thrown = none();

  private Failures failures;
  private Floats floats;

  @Before public void setUp() {
    failures = spy(new Failures());
    floats = new Floats();
    floats.failures = failures;
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    floats.assertEqual(someInfo(), null, 8f);
  }

  @Test public void should_pass_if_floats_are_equal() {
    floats.assertEqual(someInfo(), 8f, 8f);
  }

  @Test public void should_fail_if_floats_are_not_equal() {
    AssertionInfo info = someInfo();
    try {
      floats.assertEqual(info, 6f, 8f);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeEqual(6f, 8f));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }
}
