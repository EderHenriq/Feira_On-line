// Espera a página carregar
document.addEventListener("DOMContentLoaded", () => {
    
    // URL da nossa API (não muda)
    const apiUrl = "http://localhost:8080/api/feiras";

    // Elementos da página
    const listaFeirasContainer = document.getElementById("lista-feiras");
    const filtroBairroInput = document.getElementById("filtro-bairro");
    const filtroDiaSelect = document.getElementById("filtro-dia");
    const mensagemVaziaDiv = document.getElementById("mensagem-vazia");

    // "Banco de dados" local para guardar as feiras
    let todasAsFeiras = [];

    // Função principal para buscar e renderizar
    async function iniciar() {
        try {
            const feiras = await buscarFeirasDaAPI();
            todasAsFeiras = feiras; // Salva na nossa variável local
            renderizarFeiras(todasAsFeiras); // Renderiza todas na tela

            // Adiciona os "escutadores" de eventos para os filtros
            filtroBairroInput.addEventListener("input", aplicarFiltros);
            filtroDiaSelect.addEventListener("change", aplicarFiltros);

        } catch (error) {
            console.error("Erro ao iniciar:", error);
            listaFeirasContainer.innerHTML = "<p>Erro grave ao carregar. O backend está rodando?</p>";
        }
    }

    // Busca os dados da API
    async function buscarFeirasDaAPI() {
        const response = await fetch(apiUrl);
        if (!response.ok) {
            throw new Error(`Erro na API: ${response.statusText}`);
        }
        return await response.json();
    }

    // Aplica os filtros com base no que o usuário digitou/selecionou
    function aplicarFiltros() {
        const termoBairro = filtroBairroInput.value.toLowerCase();
        const diaSelecionado = filtroDiaSelect.value;

        // Filtra o array "todasAsFeiras"
        const feirasFiltradas = todasAsFeiras.filter(feira => {
            // Normaliza os dados da feira para busca
            const bairro = feira.bairro.toLowerCase();
            const local = feira.localizacao.toLowerCase();
            const dia = feira.diaDaSemana;

            // Condições do filtro
            const matchBairro = bairro.includes(termoBairro) || local.includes(termoBairro);
            const matchDia = (diaSelecionado === "") || (dia === diaSelecionado); // "" significa "Todos os dias"

            return matchBairro && matchDia;
        });

        // Renderiza apenas as feiras que passaram no filtro
        renderizarFeiras(feirasFiltradas);
    }

    // Renderiza os cards na tela
    function renderizarFeiras(feiras) {
        // Limpa a lista antiga
        listaFeirasContainer.innerHTML = "";

        // Verifica se a lista de feiras está vazia
        if (feiras.length === 0) {
            mensagemVaziaDiv.style.display = "block"; // Mostra a mensagem
        } else {
            mensagemVaziaDiv.style.display = "none"; // Esconde a mensagem
        }

        // Cria um card para cada feira
        feiras.forEach(feira => {
            const card = document.createElement("div");
            card.classList.add("card-feira");

            // Define a tag de horário
            const horaInicio = parseInt(feira.horarioInicio.split(":")[0]);
            let tagHorario = "";
            if (horaInicio < 12) {
                tagHorario = '<div class="card-tag tag-manha">Período da Manhã</div>';
            } else {
                tagHorario = '<div class="card-tag tag-tarde">Período da Tarde/Noite</div>';
            }

            // Monta o HTML interno do card
            card.innerHTML = `
                <div class="card-header">
                    <h2>${feira.nome}</h2>
                </div>
                <div class="card-body">
                    <div class="card-info">
                        <span class="material-icons-outlined">calendar_today</span>
                        <p><strong>${feira.diaDaSemana}</strong></p>
                    </div>
                    <div class="card-info">
                        <span class="material-icons-outlined">schedule</span>
                        <p>${feira.horarioInicio} às ${feira.horarioFim}</p>
                    </div>
                    <div class="card-info">
                        <span class="material-icons-outlined">apartment</span>
                        <p>${feira.bairro}</p>
                    </div>
                    <div class="card-info">
                        <span class="material-icons-outlined">location_on</span>
                        <p>${feira.localizacao}</p>
                    </div>
                </div>
                ${tagHorario}
            `;
            
            // Adiciona o card pronto na página
            listaFeirasContainer.appendChild(card);
        });
    }

    // Chama a função principal para iniciar tudo
    iniciar();
});