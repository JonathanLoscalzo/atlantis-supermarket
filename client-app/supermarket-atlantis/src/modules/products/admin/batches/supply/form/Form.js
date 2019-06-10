import React from 'react'
import { Field } from 'redux-form';
import { Row, FormGroup } from 'reactstrap'
import { reduxForm, formValueSelector } from 'redux-form'

import FormToolbar from './Toolbar';

import { RenderField } from '../../../../../shared'
import { validator } from '../../../../../shared/index'
import schema from './Validation'

const Form = props => {
    const {
        handleSubmit,
        title,
        children
    } = props

    return (
        <div>
            <h5> {title} </h5>
            <div>
                <form onSubmit={handleSubmit}>
                    <Row>
                        <FormGroup className="col-3">
                            <Field label="Cantidad" 
                                name="quantity"
                                placeholder="cantidad"
                                component={RenderField}
                                type="text" />
                        </FormGroup>
                    </Row>
                    <FormToolbar {...props} />
                </form>
            </div>
        </div>
    )
}

export default reduxForm({
    form: 'batch/supply',  // a unique identifier for this form
    validate: validator(schema),
    enableReinitialize: true
})(Form)
