
import com.example.produtos.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.produtos.Produto

class ProdutoAdapter(private val list: List<Produto>)
    : RecyclerView.Adapter<ProdutoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ProdutoViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto: Produto = list[position]
        holder.bind(produto)
    }

    override fun getItemCount(): Int = list.size

}

class ProdutoViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.activity_item, parent, false)) {
    private var nomeTxt: TextView? = null
    private var precoTxt: TextView? = null


    init {
        nomeTxt = itemView.findViewById(R.id.nomeTxt)
        precoTxt = itemView.findViewById(R.id.precoTxt)
    }

    fun bind(produto: Produto) {
        nomeTxt?.text = produto.nome
        precoTxt?.text = produto.preco.toString()
    }

}