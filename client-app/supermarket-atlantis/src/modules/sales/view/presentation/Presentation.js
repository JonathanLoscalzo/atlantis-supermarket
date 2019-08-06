import React from 'react'
import { Card, Button, CardTitle, Row, Col } from 'reactstrap';
import BatchTypes from '../../../shared/batchTypes';

const Presentation = (props) => {
    const productKeys = ["name", "brand", "description", "retailPrice", "sku", "upc"]
    return (
    <Row>
        <Col sm="12" md="12">
            <Button onClick={props.goBack} >Volver</Button>
        </Col>
        <Col sm="7" className="pt-4">
            <Card body>
                <div className="text-center">
                    <img
                        src="data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22200%22%20height%3D%22200%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20200%20200%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_16bd7ce51fd%20text%20%7B%20fill%3Argba(255%2C255%2C255%2C.75)%3Bfont-weight%3Anormal%3Bfont-family%3AHelvetica%2C%20monospace%3Bfont-size%3A10pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_16bd7ce51fd%22%3E%3Crect%20width%3D%22200%22%20height%3D%22200%22%20fill%3D%22%23777%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%2273.6328125%22%20y%3D%22104.5%22%3E200x200%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E"
                        className="rounded" alt="..." />
                </div>
            </Card>
        </Col>
        <Col sm="4" className="pt-4">
            <CardTitle><h2>{props.element["name"]}</h2></CardTitle>
            <h3> $ {props.element["retailPrice"]}</h3>
        </Col>
        <Col sm="12" className="pt-4">
        <Card body>
            <CardTitle>Producto</CardTitle>

            <ul>
                {productKeys.map(p => (<li>{p}: {props.element[p]}</li>))}
                {(<li>tipo: {BatchTypes[props.element["type"]].text}</li>)}
            </ul>
            <p>Categorias</p>
            <ul>
                {props.element.categories.length === 0
                    ? "sin categorias"
                    : props.element.categories.map(c => (<li>{c.description}</li>))}
            </ul>
            <CardTitle>Proveedor</CardTitle>
            {renderProvider(props.element.provider)}
        </Card>
    </Col>
    </Row>) 
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

