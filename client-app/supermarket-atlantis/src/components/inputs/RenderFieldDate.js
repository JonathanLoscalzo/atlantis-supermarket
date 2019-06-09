import React from 'react';
import { Label, Input, FormFeedback } from 'reactstrap';
import { DatePicker, DateTimePicker } from 'react-widgets'
import Moment from 'moment'
import momentLocalizer from 'react-widgets-moment';

Moment.locale('es')
momentLocalizer()

const RenderFieldUpdate = (props) => {
    const { showTime, input,
        input: { name, value, onChange },
        label, placeholder, type,
        meta: { error, touched, pristine } } = props;
    return (
        <div className="px-0 py-0">
            {label && <Label for={name}>{label}</Label>}
            <DateTimePicker
               // {...input}
                onChange={onChange}
                format="DD MM YYYY"
                time={showTime}
                value={!value ? null : new Date(value)}
            />
            <FormFeedback>{error}</FormFeedback>
        </div>
    )
}

export default RenderFieldUpdate;