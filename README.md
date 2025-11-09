# WhetherWatch ⌚🌤️

Um aplicativo de previsão do tempo moderno e elegante para Wear OS, desenvolvido com Jetpack Compose.

## 📱 Funcionalidades

- **Previsão do tempo em tempo real** usando a API Open-Meteo
- **Localização automática** do usuário
- **Interface amigável** otimizada para telas redondas do Wear OS
- **Dados detalhados do clima**:
  - Temperatura atual
  - Sensação térmica
  - Umidade
  - Velocidade do vento
  - Precipitação
  - Condições climáticas com ícones

## 🏗️ Arquitetura

O app segue o padrão **MVVM (Model-View-ViewModel)** com separação clara de responsabilidades:

```
📁 app/src/main/java/com/example/whetherwatch/
├── 📁 data/
│   ├── 📁 api/           # Retrofit API Service
│   ├── 📁 location/      # Location Service
│   ├── 📁 model/         # Data Models
│   └── 📁 repository/    # Weather Repository
└── 📁 presentation/
    ├── 📁 screens/       # UI Screens (Compose)
    ├── 📁 theme/         # App Theme
    └── 📁 viewmodel/     # ViewModel
```

## 🔧 Tecnologias Utilizadas

- **Kotlin** - Linguagem de programação
- **Jetpack Compose for Wear OS** - UI moderna e declarativa
- **Retrofit** - Cliente HTTP para chamadas à API
- **Coroutines** - Programação assíncrona
- **Google Play Services Location** - Serviços de localização
- **ViewModel & LiveData** - Gerenciamento de estado
- **Open-Meteo API** - Dados meteorológicos gratuitos e open-source

## 🚀 Como Executar

1. Clone o repositório
2. Abra o projeto no Android Studio
3. Sincronize as dependências Gradle
4. Execute em um emulador Wear OS ou dispositivo físico
5. Conceda as permissões de localização quando solicitado

## 📋 Permissões Necessárias

O app solicita as seguintes permissões:

- `ACCESS_FINE_LOCATION` - Para obter localização precisa
- `ACCESS_COARSE_LOCATION` - Para obter localização aproximada
- `INTERNET` - Para buscar dados da API

## 🌐 API

O app utiliza a [Open-Meteo API](https://open-meteo.com/), uma API gratuita de previsão do tempo que não requer chave de API.

### Endpoint utilizado:
```
https://api.open-meteo.com/v1/forecast
```

### Parâmetros:
- `latitude` & `longitude` - Coordenadas do usuário
- `current` - Dados meteorológicos atuais
- `hourly` - Previsão horária
- `daily` - Previsão diária

## 🎨 Design

O app apresenta:
- **Tema escuro** otimizado para Wear OS
- **Cores vibrantes** para melhor legibilidade
- **Ícones emoji** para condições climáticas
- **Layout responsivo** para diferentes tamanhos de tela
- **Animações suaves** para transições de estado

## 📱 Telas

1. **Tela de Carregamento** - Mostra enquanto busca dados
2. **Tela de Permissão** - Solicita permissão de localização
3. **Tela Principal** - Exibe informações do tempo
4. **Tela de Erro** - Mostra erros com opção de retry

## 🔄 Fluxo do App

```
Início → Verificar Permissões → Obter Localização → 
Buscar Dados do Tempo → Exibir Informações
```

## 🛠️ Desenvolvimento

### Requisitos:
- Android Studio Arctic Fox ou superior
- Android SDK 35
- Kotlin 1.9+
- Dispositivo ou emulador Wear OS com API 35+

### Build:
```bash
./gradlew assembleDebug
```

## 📄 Licença

Este projeto foi criado como exemplo educacional.

## 👨‍💻 Autor

Desenvolvido com ❤️ para Wear OS
