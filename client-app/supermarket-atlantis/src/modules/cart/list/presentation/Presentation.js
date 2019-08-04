import React from 'react'
import { Row, Col, Table } from 'reactstrap';

const Presentation = (props) => {
    return (
        <Row>
            <Col md="12" className="pt-4">
                <Table>
                    <thead>
                        <tr>
                            <th>Producto</th>
                            <th>Cantidad</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            props.items.map(item => {
                                return (
                                    <tr key={item.product.id}>
                                        <th scope="row">{item.product.name} - {item.product.brand}</th>
                                        <td>{item.quantity}</td>
                                        <td>
                                            <button onClick={() => props.deleteItem(item.product)}>
                                                Remover
                                            </button>
                                        </td>
                                    </tr>
                                )
                            })}

                    </tbody>
                </Table>
            </Col>
            <Col md="12" className="pt-4">
                FALTA FINALIZAR COMPRA
            </Col>
        </Row>
    )
}

export default Presentation