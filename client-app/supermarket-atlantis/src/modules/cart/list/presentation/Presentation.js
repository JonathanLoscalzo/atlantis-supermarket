import React from 'react'
import { Row, Col, Table, FormGroup } from 'reactstrap';
import { RenderField, RenderSelectableField, validator } from 'modules/shared';
import { Field, reduxForm } from 'redux-form';
import FormToolbar from './Toolbar';
import schema from './validation'

const Presentation = (props) => {
    return (
        <Row>
            <Col md="12" className="pt-4">
                <Table>
                    <thead>
                        <tr>
                            <th>Producto</th>
                            <th>Cantidad</th>
                            <th>Precio</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            props.element.items.map(item => {
                                return (
                                    <tr key={item.product.id}>
                                        <th scope="row">{item.product.name} - {item.product.brand}</th>
                                        <td>{item.quantity}</td>
                                        <td>{item.quantity * item.product.retailPrice}</td>
                                        <td>
                                            <button onClick={() => props.deleteItem(item.product)}>
                                                Remover
                                            </button>
                                        </td>
                                    </tr>
                                )
                            })}
                        <tr key={"unique"}>
                            <th scope="row"></th>
                            <td></td>
                            <td>{props.total}</td>
                            <td>
                            </td>
                        </tr>
                    </tbody>
                </Table>
            </Col>
            <Col className="pt-4">
                <Form
                    {...props}
                    title="Ingrese la cantidad a agregar"
                    initialValues={props.element}
                    onSubmit={props.create}
                />

            </Col>
            <Col>
                <span>
                    {
                        props.change
                    }
                </span>
            </Col>
        </Row>
    )
}

export default Presentation;

let form = ({ handleSubmit, ...props }) => (
    <form onSubmit={handleSubmit}>

        <FormGroup>
            <Field
                onChange={(event, newValue, previousValue) => props.changeSelectable(newValue)}
                label="Cantidad"
                name="payment"
                placeholder="Tipo de pago"
                options={props.payments}
                component={RenderSelectableField} />
            <Field label="Cantidad"
                onChange={(_, newValue) => props.changePay(newValue)}
                name="pay"
                placeholder="cantidad"
                component={RenderField}
                type="number" />
        </FormGroup>

        <FormToolbar submitting={props.submitting}
            pristine={props.pristine}
            reset={props.reset}
            goBack={props.goBack}
            {...props} />
    </form>)

let Form = reduxForm({
    form: 'cart/finish_sale',  // a unique identifier for this form
    validate: validator(schema),
    enableReinitialize: true
})(form)