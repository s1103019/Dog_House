import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.special_subject.R
import com.example.special_subject.ui.home.Animal

class AnimalAdapter(
    private val animalList: List<Animal>,
    private val onAnimalClick: (Animal) -> Unit // 定义点击回调
) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    private var filteredAnimalList = animalList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = filteredAnimalList[position]
        holder.imageView.setImageResource(animal.imageResId)
        holder.statusTextView.text = animal.status

        // 设置点击事件，根据 item 的状态进行操作
        holder.itemView.setOnClickListener {
            onAnimalClick(animal)
        }
    }

    override fun getItemCount(): Int {
        return filteredAnimalList.size
    }

    fun filter(query: String) {
        filteredAnimalList = if (query.isEmpty()) {
            animalList.toMutableList()
        } else {
            animalList.filter { it.status.contains(query, ignoreCase = true) }.toMutableList()
        }
        notifyDataSetChanged()
    }

    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val statusTextView: TextView = itemView.findViewById(R.id.statusTextView)
    }
}
