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


    export default App;
