package com.muhaammaad.iloader.base

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.muhaammaad.iloader.callback.CompletionCallback
import com.muhaammaad.iloader.contract.implementation.DataContractImp
import com.muhaammaad.iloader.model.DataRequest
import com.muhaammaad.iloader.util.Mapper
import com.muhaammaad.iloader.util.decodeToBitmap
import com.muhaammaad.iloader.util.withNotNullNorEmpty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

/**
 * A singleton that holds the default [ILoader] instance.
 * Responsible to query and manipulate data from provided sources
 */
object ILoader {

    /**
     * CoroutineScope with dispatcher that executes coroutines immediately when it is already in the right context
     */
    private val launcher = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    /**
     * Loads data from provided [source] and sets into provided [view]
     *
     * @param source can be url string or [DataRequest]
     * @param view image view in which source should be set
     * @param placeholder default image drawable for provided [view]
     * @param errorPlaceholder default image drawable for provided [view] in case of error
     */
    fun load(
        @NotNull source: Any,
        @NotNull view: ImageView,
        @Nullable placeholder: Drawable? = null,
        @Nullable errorPlaceholder: Drawable? = null
    ) {
        launcher.launch {
            placeholder.let {
                view.setImageDrawable(it)
            }
            (DataContractImp.getData(source) as? ByteArray)?.withNotNullNorEmpty()?.let {
                view.setImageBitmap(it.decodeToBitmap())
            } ?: kotlin.run {
                errorPlaceholder.let {
                    view.setImageDrawable(it)
                }
            }
        }
    }

    /**
     * Loads data from provided [source], maps it accordingly into provided [mapper] and returns via [delegate]
     *
     * @param source can be url string or [DataRequest]
     * @param mapper maps the source response in the given [T]
     * @param delegate provides the callback data and status about source mapping and fetching
     */
    fun <T : Any> load(
        @NotNull source: Any,
        @NotNull mapper: Mapper<ByteArray, T>,
        @NotNull delegate: CompletionCallback<T?, Boolean>
    ) {
        launcher.launch {
            (DataContractImp.getData(source) as? ByteArray)
                ?.withNotNullNorEmpty()
                ?.let { delegate.completed(mapper.map(it), true) } ?: kotlin.run {
                delegate.completed(
                    null,
                    false
                )
            }
        }
    }

    /**
     * Loads data from provided [source] and returns via [delegate]
     *
     * @param source can be url string or [DataRequest]
     * @param delegate provides the callback data and status about source mapping and fetching
     */
    fun load(
        @NotNull source: Any,
        @NotNull delegate: CompletionCallback<ByteArray?, Boolean>
    ) {
        launcher.launch {
            (DataContractImp.getData(source) as? ByteArray)
                ?.withNotNullNorEmpty()
                ?.let {
                    delegate.completed(it as? ByteArray, true)
                }
                ?: kotlin.run {
                    delegate.completed(
                        null,
                        false
                    )
                }
        }
    }
}
