import React from 'react'
import { Card, Button, CardTitle, CardText, Row, Col } from 'reactstrap';

const Presentation = (props) => {
    const { element } = props;

    return (
        <Row>
            <Col sm="12">
                <Button onClick={props.goBack} >Volver</Button>
            </Col>
            <Col sm="4">
                <Card body>
                    <CardTitle>Cliente</CardTitle>
                    <CardText>
                        {element.email}
                        {element.name}, {element.surname}
                        {element.username}
                    </CardText>
                </Card>
            </Col>
        </Row>
    )
}

export default Presentation

