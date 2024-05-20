import './App.css';

function App() {
    const modalRef = useRef();
    const fileRef = useRef();
    const [data, setData] = useState({});
    const [currentPage, setCurrentPage] = useState(0);
    const [file, setFile] = useState(undefined);
    const [values, setValues] = useState({
        name: '',
        email: '',
        phone: '',
        address: '',
        title: '',
        status: '',
    });

    const getAllContacts = async (page = 0, size = 10) => {
        try {
            setCurrentPage(page);
            const {data} = await getContacts(page, size);
            setData(data);
            console.log(data);
        } catch (error) {
            console.log(error);
            toastError(error.message);
        }
    };

    const onChange = (event) => {
        setValues({...values, [event.target.name]: event.target.value});
    };

    const handleNewContact = async (event) => {
        event.preventDefault();
        try {
            const {data} = await saveContact(values);
            const formData = new FormData();
            formData.append('file', file, file.name);
            formData.append('id', data.id);
            const {data: photoUrl} = await udpatePhoto(formData);
            toggleModal(false);
            setFile(undefined);
            fileRef.current.value = null;
            setValues({
                name: '',
                email: '',
                phone: '',
                address: '',
                title: '',
                status: '',
            })
            getAllContacts();
        } catch (error) {
            console.log(error);
            toastError(error.message);
        }
    };

    export default App;
