package com.googlecode.wicket.jquery.ui.samples.kendoui.datetimepicker;

import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;

import com.googlecode.wicket.kendo.ui.form.button.AjaxButton;
import com.googlecode.wicket.kendo.ui.form.button.Button;
import com.googlecode.wicket.kendo.ui.form.datetime.TimePicker;
import com.googlecode.wicket.kendo.ui.panel.KendoFeedbackPanel;

public class PatternTimePickerPage extends AbstractTimePickerPage
{
	private static final long serialVersionUID = 1L;

	public PatternTimePickerPage()
	{
		Form<?> form = new Form<Void>("form");
		this.add(form);

		// FeedbackPanel //
		final KendoFeedbackPanel feedback = new KendoFeedbackPanel("feedback");
		form.add(feedback);

		// TimePicker //
		final TimePicker timepicker = new TimePicker("timepicker", Model.of(new Date()), "hh:mm:ss a");
		form.add(timepicker);

		// Buttons //
		form.add(new Button("submit") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				this.info("Submitted: " + timepicker.getModelObject());
			}
		});

		form.add(new AjaxButton("button") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form)
			{
				this.info("Submitted: " + timepicker.getModelObject());
				target.add(feedback);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form)
			{
				target.add(feedback);
			}
		});
	}
}
