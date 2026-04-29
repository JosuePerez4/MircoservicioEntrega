import React, { useState, useEffect } from 'react';
import './App.css'; // Asegúrate de tener un archivo CSS básico

function App() {
  const [envios, setEnvios] = useState([]);
  const [cargando, setCargando] = useState(true);
  const [error, setError] = useState(null);

  // Esta función llama a tu Spring Boot apenas carga la página
  useEffect(() => {
    fetch('http://localhost:8082/api/entregas') // El puerto 8082 donde corre tu backend
      .then((respuesta) => {
        if (!respuesta.ok) {
          throw new Error('No se pudo conectar con el servidor de entregas');
        }
        return respuesta.json();
      })
      .then((datos) => {
        setEnvios(datos);
        setCargando(false);
      })
      .catch((err) => {
        console.error("Error fetching data:", err);
        setError(err.message);
        setCargando(false);
      });
  }, []);

  return (
    <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
      <h1>📦 Panel de Envíos Pendientes</h1>
      <p>Microservicio de Entrega - Taller Universitario</p>

      {cargando && <p>Cargando paquetes de bienvenida...</p>}
      {error && <p style={{ color: 'red' }}>Error: {error}</p>}

      {!cargando && !error && envios.length === 0 && (
        <p>No hay envíos pendientes. Esperando que el microservicio de Cliente registre usuarios...</p>
      )}

      {!cargando && !error && envios.length > 0 && (
        <table border="1" cellPadding="10" style={{ width: '100%', borderCollapse: 'collapse', marginTop: '20px' }}>
          <thead style={{ backgroundColor: '#f2f2f2' }}>
            <tr>
              <th>ID Envío</th>
              <th>ID Cliente (Original)</th>
              <th>Estado del Paquete</th>
              <th>Dirección de Destino</th>
            </tr>
          </thead>
          <tbody>
            {envios.map((envio) => (
              <tr key={envio.id}>
                <td>#{envio.id}</td>
                <td>Usuario #{envio.idClienteOriginal}</td>
                <td>
                  <span style={{ backgroundColor: '#fff3cd', padding: '5px', borderRadius: '4px', fontWeight: 'bold' }}>
                    {envio.estado}
                  </span>
                </td>
                <td>{envio.direccion}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default App;