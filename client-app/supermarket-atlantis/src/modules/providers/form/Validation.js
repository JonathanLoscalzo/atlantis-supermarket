import * as yup from 'yup'
import '../../../common/helpers/YupConfig'

const schema = yup
    .object()
    .shape({
        id: yup.string(),
        name: yup.string().required(),
        email: yup.string().email().required(),
        phone: yup.string().required(),
    })

export default schema;