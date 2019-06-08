import React from 'react'
import { Field } from 'redux-form';
import { Row, FormGroup } from 'reactstrap'

import FormToolbar from './Toolbar';

import { RenderField } from '../../../../shared'


export default props => {
    const {
        handleSubmit,
        title,
    } = props
    return (
        <div>
            <h5> {title} </h5>
            <div>
                <form onSubmit={handleSubmit}>
                    <Row>
                        <FormGroup className="col-6">
                            <Field label="Descripcion" name="description"
                                placeholder="Descripción"
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