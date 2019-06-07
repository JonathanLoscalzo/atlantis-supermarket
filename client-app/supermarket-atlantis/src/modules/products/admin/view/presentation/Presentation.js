import React from 'react'
import moment from 'moment'
import { Card, Button, CardTitle, CardText, Row, Col } from 'reactstrap';
import BatchTypes from '../../shared/batchTypes';

const Presentation = (props) => {
    const productKeys = ["name", "brand", "description", "minStock", "providerPrice", "retailPrice", "sku", "upc"]
    return (
        <Row>
            <Col sm="12">
                <Button onClick={props.goBack} >Volver</Button>
            </Col>
            <Col sm="4">
                <Card body>
                    <CardTitle>Producto</CardTitle>

                    <ul>
                        {productKeys.map(p => (<li>{p}: {props.element[p]}</li>))}
                        {(<li>tipo: {BatchTypes[props.element["type"]].text}</li>)}
                    </ul>
                    <p>Categorias</p>
                    <ul>
                        {props.element.categories.length == 0
                            ? "sin categorias"
                            : props.element.categories.map(c => (<li>{c.description}</li>))}
                    </ul>

                </Card>
            </Col>
            <Col sm="4">
                <Card body>
                    <CardTitle>Lotes</CardTitle>
                    {props.element.batches.map((b, i) => (
                        <ul>
                            <p>Lote {i + 1}</p>
                            <li>detalle: {b["detail"]}</li>
                            <li>Entrada: {moment(b["entry"]).format("DD/MM/YYYY")}</li>
                            <li>Expiracion: {moment(b["expiration"]).format("DD/MM/YYYY")}</li>
                            <li>Restante: {b["remainingUnits"]}</li>
                        </ul>
                    ))}
                </Card>
            </Col>
            <Col sm="4">
                <Card body>
                    <CardTitle>Proveedor</CardTitle>
                    {renderProvider(props.element.provider)}
                </Card>
            </Col>
        </Row>
    )
}

const renderProvider = (provider) => {
    if (provider != null) {
        return (<ul>
            <li>Nombre: {provider.name}</li>
            <li>Email: {provider.email}</li>
            <li>Telefono: {provider.phone}</li>
        </ul>)
    } else {
        return (<></>)
    }
}

export default Presentation

