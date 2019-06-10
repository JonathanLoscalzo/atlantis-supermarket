import React from 'react'
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap'
import Form from '../form/Form'

export default props => {
    return (
        <div>
            <Modal isOpen={props.isOpen}>
                <ModalHeader>Abastecer lote</ModalHeader>
                <ModalBody>
                    <Form
                        {...props}
                        title="Ingrese la cantidad a abastecer"
                        initialValues={props.element}
                        onSubmit={(values) => { props.supply(values); }}
                    />
                </ModalBody>
            </Modal>
        </div>
    )
}