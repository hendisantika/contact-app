const API_URL = 'http://localhost:8080/contacts';

export async function saveContact(contact) {
    return await axios.post(API_URL, contact);
}
