import React from 'react'
import { Card, Button, CardTitle, Row, Col } from 'reactstrap';
import moment from 'moment';
import { isClient, isAdmin } from 'common/auth';

const Presentation = (props) => {
    const { client } = props.element;
    return (
        <Row>
            <Col sm="12" md="12">
                <Button onClick={props.goBack} >Volver</Button>
            </Col>

            <Col sm="12" className="pt-4">
                <Card body>
                    <CardTitle>General</CardTitle>
                    <dl class="row">
                        <dt class="col-sm-3">Estado</dt>
                        <dd class="col-sm-9">{props.element.state}</dd>
                        <dt class="col-sm-3">Creado</dt>
                        <dd class="col-sm-9">{moment(props.element.createdAt).format("DD/MM/YYYY")}</dd>
                        {
                            props.element.invoice && (
                                <React.Fragment>
                                    <dt class="col-sm-3">Factura</dt>
                                    <dd class="col-sm-9">{props.element.invoice.id}</dd>
                                </React.Fragment>)
                        }
                    </dl>
                </Card>
            </Col>

            {isAdmin() &&
                <Col sm="12" className="pt-4">
                    <Card body>
                        <CardTitle>Cliente</CardTitle>
                        <dl class="row">
                            <dt class="col-sm-3">Nombre</dt>
                            <dd class="col-sm-9">{client.name}</dd>
                            <dt class="col-sm-3">Apellido</dt>
                            <dd class="col-sm-9">{client.surname}</dd>
                            <dt class="col-sm-3">Documento</dt>
                            <dd class="col-sm-9">{client.document}</dd>
                            <dt class="col-sm-3">Email</dt>
                            <dd class="col-sm-9">{client.email}</dd>
                        </dl>
                    </Card>
                </Col>
            }
            <Col sm="12" className="pt-4">
                <Card body>
                    <CardTitle>Items</CardTitle>
                    <table class="table table-dark">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Producto</th>
                                <th scope="col">Tipo</th>
                                <th scope="col">Total (unitario)</th>
                                <th scope="col">Unidades</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                props.element.items.map(x => {
                                    return (
                                        <tr>
                                            <th scope="row">{x.line}</th>
                                            <td>{x.product.name} - {x.product.brand}</td>
                                            <td>{x.product.type}</td>
                                            <td>${x.total} (${x.pricePerUnit})</td>
                                            <td>{x.units} unidades</td>
                                        </tr>
                                    );
                                })
                            }
                        </tbody>
                    </table>
                </Card>
            </Col>

        </Row>)
}

export default Presentation

