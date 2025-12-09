//package com.sise.tandina.presentacion.inicio;
//
//public class PreguntasActivity {
//
//    private TextView txtCategoria, txtPuntaje, txtTiempo, txtPregunta;
//
//    private Button btnOp1, btnOp2, btnOp3, btnOp4;
//
//    private PreguntasViewModel viewModel;
//
//    private int indexPregunta = 0;
//
//    private int puntaje = 0;
//
//    private List<Pregunta> listaPreguntas;
//
//    private CountDownTimer timer;
//
//    @Override
//
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_preguntas);
//
//        // 🟦 1. Vincular vistas
//
//        txtCategoria = findViewById(R.id.txtCategoria);
//
//        txtPuntaje  = findViewById(R.id.txtPuntaje);
//
//        txtTiempo   = findViewById(R.id.txtTiempo);
//
//        txtPregunta = findViewById(R.id.txtPregunta);
//
//        btnOp1 = findViewById(R.id.btnOp1);
//
//        btnOp2 = findViewById(R.id.btnOp2);
//
//        btnOp3 = findViewById(R.id.btnOp3);
//
//        btnOp4 = findViewById(R.id.btnOp4);
//
//        // 🟦 2. Recibir categoría enviada desde InicioActivity
//
//        int idCategoria = getIntent().getIntExtra("idCategoria", 0);
//
//        String nombreCategoria = getIntent().getStringExtra("nombreCategoria");
//
//        txtCategoria.setText("Categoría: " + nombreCategoria);
//
//        // 🟦 3. Inicializar ViewModel
//
//        viewModel = new ViewModelProvider(this).get(PreguntasViewModel.class);
//
//        // 🟦 4. Llamar API para traer preguntas
//
//        viewModel.getPreguntas(idCategoria).observe(this, response -> {
//
//            if (response != null && response.getData() != null) {
//
//                listaPreguntas = response.getData();
//
//                if (!listaPreguntas.isEmpty()) {
//
//                    mostrarPregunta();
//
//                }
//
//            }
//
//        });
//
//        // 🟦 5. Listener de botones
//
//        View.OnClickListener listener = v -> verificarRespuesta((Button) v);
//
//        btnOp1.setOnClickListener(listener);
//
//        btnOp2.setOnClickListener(listener);
//
//        btnOp3.setOnClickListener(listener);
//
//        btnOp4.setOnClickListener(listener);
//
//    }
//
//    private void mostrarPregunta() {
//
//        if (indexPregunta >= listaPreguntas.size()) {
//
//            finish(); // Puedes pasar a actividad final
//
//            return;
//
//        }
//
//        Pregunta p = listaPreguntas.get(indexPregunta);
//
//        txtPregunta.setText(p.getTitulo());
//
//        btnOp1.setText(p.getOp1());
//
//        btnOp2.setText(p.getOp2());
//
//        btnOp3.setText(p.getOp3());
//
//        btnOp4.setText(p.getOp4());
//
//        iniciarTiempo();
//
//    }
//
//    private void iniciarTiempo() {
//
//        if (timer != null) timer.cancel();
//
//        timer = new CountDownTimer(20000, 1000) {
//
//            public void onTick(long ms) {
//
//                txtTiempo.setText("" + (ms / 1000));
//
//            }
//
//            public void onFinish() {
//
//                indexPregunta++;
//
//                mostrarPregunta();
//
//            }
//
//        }.start();
//
//    }
//
//    private void verificarRespuesta(Button btn) {
//
//        timer.cancel();
//
//        Pregunta p = listaPreguntas.get(indexPregunta);
//
//        if (btn.getText().equals(p.getRespuestaCorrecta())) {
//
//            puntaje += 5;
//
//            txtPuntaje.setText("Puntaje: " + puntaje);
//
//        }
//
//        indexPregunta++;
//
//        mostrarPregunta();
//
//    }
//
//}
