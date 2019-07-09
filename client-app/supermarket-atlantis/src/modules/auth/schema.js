import * as yup from 'yup'
import message from '../../config/messages'

const schema = (param) => {
    return yup.object()
        .shape({
            username: yup.string()
                .required(message.REQUIRED),
            password: yup.string()
                .required(message.REQUIRED),
            isSignup: yup.boolean().default(false),
            name: yup.string()
                .when("isSignup", {
                    is: true,
                    then: yup.string().required(message.REQUIRED),
                }),      
            surname: yup.string()
                .when("isSignup", {
                    is: true,
                    then: yup.string().required(message.REQUIRED),
                }),
            email: yup.string()
                .when("isSignup", {
                    is: true,
                    then: yup.string().email().required(message.REQUIRED),
                }),
            document: yup.string()
                .when("isSignup", {
                    is: true,
                    then: yup.string().required(message.REQUIRED),
                }),
        })
}

export default schema();
