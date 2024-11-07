import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.special_subject.R
import com.example.special_subject.ui.home.Animal

class AnimalAdapter(private val animalList: List<Animal>) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    // 用于保存原始的动物列表数据和过滤后的列表
    private var filteredAnimalList = animalList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = filteredAnimalList[position]
        holder.imageView.setImageResource(animal.imageResId)
        holder.statusTextView.text = animal.status
    }

    override fun getItemCount(): Int {
        return filteredAnimalList.size
    }

    // 过滤功能，根据查询文本过滤动物列表
    fun filter(query: String) {
        filteredAnimalList = if (query.isEmpty()) {
            animalList.toMutableList()
        } else {
            animalList.filter { it.status.contains(query, ignoreCase = true) }.toMutableList()
        }
        notifyDataSetChanged()
    }

    // 定义 ViewHolder 类
    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val statusTextView: TextView = itemView.findViewById(R.id.statusTextView)
    }
}
