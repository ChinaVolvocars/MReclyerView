package tiger.com.mreclyerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recylerview)
    RecyclerView recylerview;
    private List<String> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mDoubleClickExitHelper = new DoubleClickExitHelper(this);
        data = new ArrayList();
        for (int i = 0; i < 100; i++) {
            data.add("a" + i);
        }

        RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = View.inflate(parent.getContext(), R.layout.simple, null);
                return new myViewHolder(itemView);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                myViewHolder myViewHolder = (myViewHolder) holder;
                myViewHolder.textView.setText(data.get(position));

            }

            @Override
            public int getItemCount() {
                return data == null ? 0 : data.size();
            }
        };
        recylerview.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.VERTICAL, false));
        recylerview.setAdapter(adapter);

    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv)
        TextView textView;

        public myViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    DoubleClickExitHelper mDoubleClickExitHelper;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean flag = true;
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return mDoubleClickExitHelper.onKeyDown(keyCode, recylerview);
        }
        return flag;

    }
}
