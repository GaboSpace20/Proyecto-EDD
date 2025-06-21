class InterfazSopaDeLetras {
    constructor() {
        this.crearInterfaz();
    }

    crearInterfaz() {
        // Crear contenedor principal
        document.body.innerHTML = `
            <div class="contenedor-juego" style="
                font-family: 'Poppins', sans-serif;
                background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
                min-height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
                margin: 0;
            ">
                <!-- Pantalla de Inicio -->
                <div id="pantalla-inicio" class="pantalla-inicio" style="
                    background: white;
                    border-radius: 1rem;
                    box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 极.1);
                    padding: 2rem;
                    text-align: center;
                ">
                    <h1 style="font-size: 2.25rem; font-weight: 700; color: black; margin-bottom: 1.5rem;">Sopa de Letras</h1>
                    <div class="contenedor-botones" style="display: flex; flex-direction: column; gap: 1rem; max-width: 20rem; margin: 0 auto;">
                        <button id="btn-iniciar" style="
                            background: #4f46e5;
                            color: white;
                            font-weight: 700;
                            padding: 0.75rem 1.5rem;
                            border-radius: 0.5rem;
                            border: none;
                            cursor: pointer;
                        ">Iniciar</button>
                        <button id="btn-salir" style="
                            background: #ef4444;
                            color: white;
                            font-weight: 700;
                            padding: 0.75rem 1.5rem;
                            border-radius: 0.5rem;
                            border: none;
                            cursor: pointer;
                        ">Salir</button>
                    </div>
                </div>

                <!-- Pantalla de Juego -->
                <div id="pantalla-juego" class="pantalla-juego" style="
                    background: white;
                    border-radius: 1rem;
                    box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.1);
                    padding: 2rem;
                    display: none;
                    max-width: 800px;
                    width: 100%;
                ">
                    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 1.5rem;">
                        <h2 style="font-size: 1.5rem; font-weight: 700; color: #4f46e5;">Sopa de Letras</h2>
                        <div style="display: flex; gap: 0.5rem;">
                            <button id="btn-pista" style="
                                background: #eab308;
                                color: white;
                                font-weight: 700;
                                padding: 0.5rem 1rem;
                                border-radius: 0.5rem;
                                border: none;
                                cursor: pointer;
                            ">Pista</button>
                            <button id="btn-salir-juego" style="
                                background: #ef4444;
                                color: white;
                                font-weight: 700;
                                padding: 0.5rem 1rem;
                                border-radius: 0.5rem;
                                border: none;
                                cursor: pointer;
                            ">Salir</button>
                        </div>
                    </div>

                    <div style="display: flex; flex-direction: column; gap: 2rem;">
                        <div id="tablero" class="tablero-sopa-letras" style="
                            display: grid;
                            grid-template-columns: repeat(10, 1fr);
                            gap: 2px;
                            margin: 0 auto;
                        ">
                            <!-- Celdas de ejemplo (10x10) -->
                            ${Array(10).fill().map((_, y) => 
                                Array(10).fill().map((_, x) => `
                                    <div class="celda" data-x="${x}" data-y="${y}" style="
                                        width: 40px;
                                        height: 40px;
                                        display: flex;
                                        justify-content: center;
                                        align-items: center;
                                        background-color: white;
                                        border-radius: 5px;
                                        font-weight: 600;
                                        cursor: pointer;
                                        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
                                    ">${String.fromCharCode(65 + Math.floor(Math.random() * 26))}</div>
                                `).join('')
                            ).join('')}
                        </div>

                        <div style="background: #eef2ff; padding: 1rem; border-radius: 0.5rem;">
                            <h3 style="font-size: 1.125rem; font-weight: 600; color: #4f46e5; margin-bottom: 1rem;">Palabras a Encontrar:</h3>
                            <ul id="lista-palabras" class="lista-palabras" style="
                                columns: 2;
                                column-gap: 2rem;
                            ">
                                <!-- Lista de palabras de ejemplo -->
                                <li style="margin-bottom: 0.5rem; padding: 0.25rem 0.5rem; border-radius: 5px;">manzana</li>
                                <li style="margin-bottom: 0.5rem; padding: 0.25rem 0.5rem; border-radius: 5px;">banana</li>
                                <li style="margin-bottom: 0.5rem; padding: 0.25rem 0.5rem; border-radius: 5px;">naranja</li>
                                <li style="margin-bottom: 0.5rem; padding: 0.25rem 0.5rem; border-radius: 5px;">uva</li>
                                <li style="margin-bottom: 0.5rem; padding: 0.25rem 0.5rem; border-radius: 5px;">pera</li>
                                <li style="margin-bottom: 0.5rem; padding: 0.25rem 0.5rem; border-radius: 5px;">limón</li>
                                <li style="margin-bottom: 0.5rem; padding: 0.25rem 0.5rem; border-radius: 5px;">durazno</li>
                                <li style="margin-bottom: 0.5rem; padding: 0.25rem 0.5rem; border-radius: 5px;">mango</li>
                                <li style="margin-bottom: 0.5rem; padding: 0.25rem 0.5rem; border-radius: 5px;">frutilla</li>
                                <li style="margin-bottom: 0.5rem; padding: 0.25rem 0.5rem; border-radius: 5px;">melón</li>
                            </ul>
                        </div>

                        <div style="margin-top: 1.5rem; display: flex; justify-content: space-between; align-items: center;">
                            <span style="color: #6b7280;">
                                Palabras encontradas: <span id="contador-encontradas">0</span>/<span id="total-palabras">10</span>
                            </span>
                            <button id="btn-nuevo-juego" style="
                                background: #4f46e5;
                                color: white;
                                font-weight: 700;
                                padding: 0.5rem 1rem;
                                border-radius: 0.5rem;
                                border: none;
                                cursor: pointer;
                            ">Nuevo Juego</button>
                        </div>
                    </div>
                </div>
            </div>
        `;
    }

    mostrarPantallaInicio() {
        document.getElementById('pantalla-inicio').style.display = 'block';
        document.getElementById('pantalla-juego').style.display = 'none';
    }

    mostrarPantallaJuego() {
        document.getElementById('pantalla-inicio').style.display = 'none';
        document.getElementById('pantalla-juego').style.display = 'block';
    }
}

// Inicializar la interfaz cuando cargue la página
window.addEventListener('DOMContentLoaded', () => {
    const interfaz = new InterfazSopaDeLetras();
    
    document.getElementById('btn-iniciar').addEventListener('click', () => interfaz.mostrarPantallaJuego());
    document.getElementById('btn-salir').addEventListener('click', () => alert('¿Estás seguro que quieres salir?'));
    document.getElementById('btn-salir-juego').addEventListener('click', () => interfaz.mostrarPantallaInicio());
    document.getElementById('btn-nuevo-juego').addEventListener('click', () => alert('Nuevo juego iniciado'));
    document.getElementById('btn-pista').addEventListener('click', () => alert('Mostrando pista...'));
});
