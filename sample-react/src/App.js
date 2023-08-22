import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Person() {
  // personsは現在のPersonエンティティのリストを保持します。
  const [persons, setPersons] = useState([]);
  // nameは新しいPersonエンティティの名前を保持します。
  const [name, setName] = useState('');

  // コンポーネントがマウントされた後にPersonエンティティのリストを取得します。
  useEffect(() => {
    axios.get('/persons')
      .then(response => {
        // 取得したデータをローカルの状態に設定します。
        setPersons(response.data);
      });
  }, []);

  // Personエンティティを追加する関数
  const addPerson = () => {
    // POSTリクエストで新しいPersonエンティティをサーバーに送信します。
    axios.post('/person', { name })
      .then(() => {
        // 入力フィールドをリセットします。
        setName('');
        // 新しいPersonエンティティのリストを取得します。
        return axios.get('/persons');
      })
      .then(response => {
        // 新しいリストをローカルの状態に設定します。
        setPersons(response.data);
      });
  };

  // Personエンティティを削除する関数
  const deletePerson = (id) => {
    // DELETEリクエストで指定されたIDのPersonエンティティをサーバーから削除します。
    axios.delete(`/person/${id}`)
      .then(() => {
        // 削除後、更新されたPersonエンティティのリストを取得します。
        return axios.get('/persons');
      })
      .then(response => {
        // 新しいリストをローカルの状態に設定します。
        setPersons(response.data);
      });
  };

  return (
    <div>
      <h1>Persons</h1>
      <div>
        <input
          type="text"
          placeholder="Name"
          value={name}
          // ユーザーが名前を入力するたびに状態を更新します。
          onChange={e => setName(e.target.value)}
        />
        <button onClick={addPerson}>Add</button>
      </div>
      <ul>
        {persons.map(person => (
          <li key={person.id}>
            {person.name} <button onClick={() => deletePerson(person.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Person;
