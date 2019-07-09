import React from 'react'
import { Card, Button, CardTitle, CardText, Row, Col } from 'reactstrap';

const Presentation = (props) => {
    return (
        <Row>
            <Col sm="12">
                <Button onClick={props.goBack} >Volver</Button>
            </Col>
            <Col sm="4">
                <Card body>
                    <CardTitle>Cliente</CardTitle>
                </Card>
            </Col>
        </Row>
    )
}

export default Presentation

