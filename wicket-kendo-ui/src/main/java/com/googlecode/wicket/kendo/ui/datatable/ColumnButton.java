/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.wicket.kendo.ui.datatable;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.io.IClusterable;

/**
 * Provides the button object that can be used in {@link DataTable}
 *
 * @author Sebastien Briquet - sebfz1
 */
public class ColumnButton implements IClusterable
{
	private static final long serialVersionUID = 1L;

	private static short sequence = 0;

	/**
	 * Gets the next id-sequence. This is used to generate the markupId
	 * @return 0x0000 to 0x7FFF
	 */
	private static synchronized int nextSequence()
	{
		return ColumnButton.sequence++ % Short.MAX_VALUE;
	}

	private final int id;
	private final String text;
	private final String property;

	/**
	 * Constructor
	 * @param text the button's text
	 * @param property the property used to retrieve the row's object value
	 */
	public ColumnButton(String text, String property)
	{
		this.id = ColumnButton.nextSequence();
		this.text = text;
		this.property = property;
	}

	/**
	 * Constructor
	 * @param model the button's text model
	 * @param property the property used to retrieve the row's object value
	 */
	@Deprecated
	//XXX: to be removed in next version
	public ColumnButton(final IModel<String> model, String property)
	{
		this(model.getObject(), property);
	}

	public String getProperty()
	{
		return this.property;
	}

	/**
	 * Gets the markupId of the row button.
	 *
	 * @return the markupId
	 */
	protected String getMarkupId()
	{
		return String.format("btn%02x", this.id).toLowerCase();
	}

	protected String getCSSClass()
	{
		return this.getMarkupId();
	}

	// Methods //

	@Override
	public int hashCode()
	{
		return this.id;
	}

	/**
	 * Indicates whether this {@link ColumnButton} is equal to another {@link ColumnButton}.<br/>
	 * Are considered equals buttons having the same text representation, which is the text supplied to the constructor (if {@link #toString()} is not overridden).
	 *
	 * @param object a {@link ColumnButton} to compare to
	 * @return true if considered as equal
	 */
	@Override
	public boolean equals(Object object)
	{
		if (object instanceof ColumnButton)
		{
			return this.match(object.toString());
		}

		return super.equals(object);
	}

	/**
	 * Indicates whether this {@link ColumnButton} text representation ({@link #toString()}) match to the supplied text.
	 *
	 * @param text the text to compare to
	 * @return true if equal
	 */
	public boolean match(String text)
	{
		return text.equals(this.toString()); // let throw a NPE
	}

	@Override
	public String toString()
	{
		return this.text;
	}
}
