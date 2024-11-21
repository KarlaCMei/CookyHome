package com.karla.cookyhome.utils.firebase

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task

/**
 * Su propósito es proporcionar una estructura común para manejar tareas asíncronas (como solicitudes
 * de Firebase) con un flujo estándar de mostrar y ocultar un indicador de carga, y manejar tanto los
 * resultados exitosos como los fallos.
 */

abstract class CustomOnCompleteListener<T> : OnCompleteListener<T> {
    init {
        showLoaging()
    }

    override fun onComplete(task: Task<T>) {
        hideLoading()
        if (task != null && task.isSuccessful) {
            onSuccess(task.result)
        } else {
            onFailure(task.exception)
        }
    }

    abstract fun onSuccess(task: T)
    abstract fun onFailure(throwable: Throwable?)
    abstract fun showLoaging()
    abstract fun hideLoading()
}