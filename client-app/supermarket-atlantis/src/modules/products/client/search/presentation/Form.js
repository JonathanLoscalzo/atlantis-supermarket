import React from 'react'
import {
    Col, Row,
    FormGroup, FormFeedback,
    InputGroup, InputGroupAddon, Button, Input,
    UncontrolledCollapse, CardBody, Card
} from 'reactstrap'

import { Field } from 'redux-form';
import {
    RenderField,
} from '../../../../shared'


export default (props) => {

    const {
        handleSubmit,
        submitting,
        enableAdvanceSearch
    } = props

    return (
        <form onSubmit={handleSubmit}>
            <Col md="12">
                <FormGroup>
                    <InputGroup>
                        <Field
                            label="Nombre"
                            name="name"
                            placeholder="Buscar..."
                            component={InputSimple} type="text" />
                        <InputGroupAddon addonType="append">
                            <Button type="submit" disabled={submitting}>Buscar</Button>
                            <Button onClick={() => enableAdvanceSearch()}
                                color="primary"
                                id="toggler">
                                Búsqueda avanzada
                                    </Button>
                        </InputGroupAddon>
                    </InputGroup>
                </FormGroup>
            </Col>
            <Col md="12">
                <UncontrolledCollapse toggler="#toggler">
                    <Card>
                        <CardBody>
                            <FormGroup>
                                <Row>
                                    <Col md="5">
                                        <Field
                                            label="Precio Mínimo"
                                            name="minPrice"
                                            placeholder="Precio Min"
                                            component={RenderField} type="number" />
                                    </Col>
                                    <Col md="5">
                                        <Field
                                            label="Precio Máximo"
                                            name="maxPrice"
                                            placeholder="Precio Max"
                                            component={RenderField} type="number" />
                                    </Col>
                                </Row>
                            </FormGroup>
                        </CardBody>
                    </Card>
                </UncontrolledCollapse>
            </Col>
        </form>)
}

const InputSimple = (props) => {
    const {
        input, input: { name },
        placeholder,
        type,
        meta: { error, touched, pristine } } = props;
    return (
        <div>
            <Input
                {...input}
                invalid={touched && error}
                name={name} id={name}
                placeholder={placeholder}
                type={type}></Input>
            <FormFeedback>{error}</FormFeedback>
        </div>
    )
}