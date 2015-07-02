package com.google.android.finsky.detailspage;

import android.os.Handler;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.finsky.utils.Lists;
import java.util.Collection;
import java.util.List;

public class ModulesAdapter extends Adapter<ModuleViewHolder> {
    private Handler mHandler;
    private List<Module> mModules;

    public static abstract class Module {
        private ModuleViewHolder mModuleViewHolder;

        public abstract void bindView(View view);

        public abstract int getLayoutResId();

        public void onRecycleView(View view) {
        }
    }

    protected static class ModuleViewHolder extends ViewHolder {
        Module module;

        public ModuleViewHolder(View itemView) {
            super(itemView);
        }
    }

    public ModulesAdapter(List<Module> modules) {
        this.mHandler = new Handler();
        if (modules != null) {
            this.mModules = Lists.newArrayList((Collection) modules);
        } else {
            this.mModules = Lists.newArrayList();
        }
    }

    public void addModule(Module module, int position) {
        this.mModules.add(position, module);
        notifyItemInserted(position);
    }

    public void removeModule(int position) {
        this.mModules.remove(position);
        notifyItemRemoved(position);
    }

    public void refreshModuleWithAnimation(int position) {
        notifyItemChanged(position);
    }

    public void refreshModuleWithoutAnimation(int position) {
        final Module module = (Module) this.mModules.get(position);
        this.mHandler.post(new Runnable() {
            public void run() {
                if (module.mModuleViewHolder != null) {
                    module.bindView(module.mModuleViewHolder.itemView);
                }
            }
        });
    }

    public int getItemViewType(int position) {
        return ((Module) this.mModules.get(position)).getLayoutResId();
    }

    public ModuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ModuleViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }

    public void onBindViewHolder(ModuleViewHolder moduleViewHolder, int position) {
        Module module = (Module) this.mModules.get(position);
        module.mModuleViewHolder = moduleViewHolder;
        moduleViewHolder.module = module;
        module.bindView(moduleViewHolder.itemView);
    }

    public void onViewRecycled(ModuleViewHolder moduleViewHolder) {
        if (moduleViewHolder.module != null) {
            moduleViewHolder.module.onRecycleView(moduleViewHolder.itemView);
            moduleViewHolder.module.mModuleViewHolder = null;
            moduleViewHolder.module = null;
        }
        super.onViewRecycled(moduleViewHolder);
    }

    public void onDestroyed() {
        for (int i = 0; i < this.mModules.size(); i++) {
            Module module = (Module) this.mModules.get(i);
            if (module.mModuleViewHolder != null) {
                module.onRecycleView(module.mModuleViewHolder.itemView);
                module.mModuleViewHolder.module = null;
                module.mModuleViewHolder = null;
            }
        }
    }

    public int getItemCount() {
        return this.mModules.size();
    }
}
