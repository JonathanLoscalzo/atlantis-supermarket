import React from 'react'
import { Card, Button, CardTitle, CardText, Row, Col } from 'reactstrap';

const Presentation = (props) => {
    const { element } = props;

    return (
        <React.Fragment>
            <Row>
                <Col sm="12">
                    <Button onClick={props.goBack} >Volver</Button>
                </Col>
                <Col sm="6" className={"p-3"}>
                    <Card body>
                        <CardTitle><h5>Cliente</h5></CardTitle>
                        <CardText>
                            <p>email: {element.email}</p>
                            <p>fullname: {element.name}, {element.surname}</p>
                            <p>username: {element.username}</p>
                        </CardText>
                    </Card>
                </Col>
            </Row>
        </React.Fragment>
    )
}

export default Presentation

