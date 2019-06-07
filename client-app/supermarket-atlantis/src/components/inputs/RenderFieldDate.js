import React from 'react';
import { Label, Input, FormFeedback } from 'reactstrap';
import DateTimePicker from 'react-widgets/lib/DateTimePicker'

const RenderFieldUpdate = (props) => {
    const { showTime, input, input: { name, value }, label, placeholder, type, meta: { error, touched, pristine } } = props;
    return (
        <div className="px-0 py-0">
            {label && <Label for={name}>{label}</Label>}
            {/* <Input 
            valid={touched && !error && !pristine} 
            invalid={touched && error} {...input} 
            name={name} id={name} 
            placeholder={placeholder} 
            type={type}></Input> */}
            <DateTimePicker
                {...input}
                format="DD MM YYYY"
                time={showTime}
                value={!value ? null : new Date(value)}
            />
            <FormFeedback>{error}</FormFeedback>
        </div>
    )
}

export default RenderFieldUpdate;