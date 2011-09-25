/*
 * Created on Dec 24, 2010
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

import static org.fest.assertions.error.ShouldBeWithin.shouldBeWithin;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;

import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.*;

import org.fest.assertions.core.AssertionInfo;

/**
 * Tests for <code>{@link Dates#assertIsWithinMillisecond(AssertionInfo, Date, int)}</code>.
 *
 * @author Joel Costigliola
 */
public class Dates_assertIsWithinMillisecond_Test extends AbstractDatesTest {

  @Override
  @Before
  public void setUp() {
    super.setUp();
    actual = new Date(parseDatetime("2011-01-01T03:49:17").getTime() + 13);
  }

  @Test
  public void should_fail_if_actual_is_not_within_given_millisecond() {
    AssertionInfo info = someInfo();
    int millisecond = 5;
    try {
      dates.assertIsWithinMillisecond(info, actual, millisecond);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeWithin(actual, "millisecond", millisecond));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    dates.assertIsWithinMillisecond(someInfo(), null, 13);
  }

  @Test
  public void should_pass_if_actual_is_within_given_millisecond() {
    dates.assertIsWithinMillisecond(someInfo(), actual, 13);
  }

}
