package br.ufes.acessousuarios.presenter;

import br.ufes.acessousuarios.view.ConfiguracaoView;
import br.ufes.log.LogAdapter;
import java.util.HashMap;
import java.util.Map;

public class ConfiguracaoPresenter {

    private ConfiguracaoView view;
    private LogAdapter logAdapter;
    private Map<String, String> configuracoes; // Mapa para armazenar as configurações

    public ConfiguracaoPresenter(ConfiguracaoView view, String formatoLog, String filePath) {
        this.view = view;
        this.logAdapter = new LogAdapter(formatoLog, filePath);
        this.configuracoes = new HashMap<>();
        configurarView();
    }

    private void configurarView() {
        // Configurações iniciais e listeners da View
        view.getBtnSalvar().addActionListener((e) -> salvarConfiguracoes());
        view.getBtnCancelar().addActionListener((e) -> cancelarConfiguracoes());
        carregarConfiguracoesIniciais(); // Carrega as configurações no início
    }

    private void salvarConfiguracoes() {
        try {
            // Coleta as configurações da view
            String linguagem = view.getTxtLinguagem().getText();
            String tema = view.getTxtTema().getText();

            // Salva as configurações no mapa
            configuracoes.put("linguagem", linguagem);
            configuracoes.put("tema", tema);

            // Aqui você pode adicionar lógica para persistir as configurações, se necessário.
            logAdapter.log("Salvar Configurações", "Configurações do Sistema", "Usuário atual", true, null);

            // Exibe mensagem de sucesso para o usuário
            view.exibirMensagem("Configurações salvas com sucesso!");

        } catch (Exception ex) {
            // Caso ocorra algum erro, grava no log e exibe erro na view
            logAdapter.log("Erro ao Salvar Configurações", "Configurações do Sistema", "Usuário atual", false, ex.getMessage());
            view.exibirErro("Erro ao salvar as configurações: " + ex.getMessage());
        }
    }

    private void cancelarConfiguracoes() {
        try {
            // Recarrega as configurações iniciais, como uma espécie de "desfazer"
            carregarConfiguracoesIniciais();
            logAdapter.log("Cancelar Configurações", "Configurações do Sistema", "Usuário atual", true, null);

            // Exibe mensagem de cancelamento para o usuário
            view.exibirMensagem("Configurações foram restauradas.");

        } catch (Exception ex) {
            // Caso ocorra algum erro, grava no log e exibe erro na view
            logAdapter.log("Erro ao Cancelar Configurações", "Configurações do Sistema", "Usuário atual", false, ex.getMessage());
            view.exibirErro("Erro ao cancelar as configurações: " + ex.getMessage());
        }
    }

    private void carregarConfiguracoesIniciais() {
        try {
            // Carregar as configurações existentes (pode ser de um arquivo ou banco de dados)
            String linguagem = configuracoes.getOrDefault("linguagem", "Português");
            String tema = configuracoes.getOrDefault("tema", "Claro");

            // Atualiza os campos na View
            view.getTxtLinguagem().setText(linguagem);
            view.getTxtTema().setText(tema);

            logAdapter.log("Carregar Configurações Iniciais", "Configurações do Sistema", "Usuário atual", true, null);

        } catch (Exception ex) {
            // Caso ocorra algum erro, grava no log e exibe erro na view
            logAdapter.log("Erro ao Carregar Configurações Iniciais", "Configurações do Sistema", "Usuário atual", false, ex.getMessage());
            view.exibirErro("Erro ao carregar as configurações: " + ex.getMessage());
        }
    }
}