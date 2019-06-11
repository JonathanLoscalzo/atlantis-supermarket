import React from 'react'
import { Field } from 'redux-form';
import { Row, FormGroup } from 'reactstrap'

import FormToolbar from './Toolbar';
import { Spinner } from '../../../../shared'


import { RenderField, RenderSelectableField, RenderDateField } from '../../../../shared'
import batchTypes from '../../shared/batchTypes'

const types = [
    { value: batchTypes.DEFAULT.enum, label: batchTypes.DEFAULT.text },
    { value: batchTypes.EXPIRATION.enum, label: batchTypes.EXPIRATION.text }
]
export default props => {
    const {
        handleSubmit,
        title,
        element
    } = props

    return (
        <div>
            <h5> {title} </h5>
            <div>
                <form onSubmit={handleSubmit}>
                    <Row>
                        <FormGroup className="col-12">
                            <Field
                                className="col-3"
                                label="Detail"
                                name="detail"
                                placeholder="Detalle"
                                component={RenderField}
                                type="text" />
                            {
                                element.type != "DEFAULT" &&
                                <Field
                                    className="col-3"
                                    label="Expiración"
                                    name="expiration"
                                    placeholder="expiration"
                                    showTime={false}
                                    component={RenderDateField}
                                    type="date" />
                            }
                            <Field
                                className="col-3"
                                label="Unidades"
                                name="remainingUnits"
                                placeholder="100.20"
                                component={RenderField}
                                type="text" />

                            {/* <Field label="Proveedor"
                                name="providerId"
                                placeholder="Proveedor"
                                options={providersOptions}
                                component={RenderSelectableField} /> */}
                        </FormGroup>
                    </Row>
                    <Spinner loading={props.loading}>
                        <FormToolbar {...props} />
                    </Spinner>

                </form>
            </div>
        </div>
    )
}