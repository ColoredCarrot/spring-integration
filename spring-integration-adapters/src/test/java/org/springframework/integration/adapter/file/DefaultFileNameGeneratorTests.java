/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.integration.adapter.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.springframework.integration.message.GenericMessage;
import org.springframework.integration.message.Message;

/**
 * @author Mark Fisher
 */
public class DefaultFileNameGeneratorTests {

	@Test
	public void testWithFileNamePropertyProvided() {
		Message<String> message = new GenericMessage<String>("123", "testing");
		message.getHeader().setProperty(FileNameGenerator.FILENAME_PROPERTY_KEY, "foo.bar");
		FileNameGenerator generator = new DefaultFileNameGenerator();
		String filename = generator.generateFileName(message);
		assertEquals("foo.bar", filename);
	}

	@Test
	public void testWithoutFileNamePropertyProvided() {
		Message<String> message = new GenericMessage<String>("123", "testing");
		FileNameGenerator generator = new DefaultFileNameGenerator();
		String filename = generator.generateFileName(message);
		assertTrue(filename.startsWith("123-"));
		assertTrue(filename.endsWith(".msg"));
	}

}
