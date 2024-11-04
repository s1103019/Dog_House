// AnimalAdapter.kt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.special_subject.R
import com.example.special_subject.ui.home.Animal

class AnimalAdapter(private val animalList: List<Animal>) :
    RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    // 創建 ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    // 綁定數據到 ViewHolder
    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animalList[position]
        holder.imageView.setImageResource(animal.imageResId)
        holder.statusTextView.text = animal.status
    }

    // 回傳列表的項目數
    override fun getItemCount(): Int {
        return animalList.size
    }

    // 定義 ViewHolder 類
    class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val statusTextView: TextView = itemView.findViewById(R.id.statusTextView)
    }
}
