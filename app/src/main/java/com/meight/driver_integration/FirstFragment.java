package com.meight.driver_integration;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.meight.driver_integration.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private static final String knownWorkspace = null; // replace by a workspace the driver is in
    private static final String knownTask = null; // replace by a task yet to be completed, assigned to the driver
    private static final String unknownWorkspace = "wor_xxxxx";
    private static final String unknownTask = "tas_xxxxx";
    private static final String finishedTask = null; // replace by a task that was already completed

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.geoIntent.setOnClickListener(view15 -> {
            Uri geo = Uri.parse("geo:41.1579438,-8.6291053");
            Intent geoIntent = new Intent(Intent.ACTION_VIEW, geo);

            try {
                startActivity(geoIntent);
            }catch (ActivityNotFoundException ignore){

            }
        });

        binding.geoIntentWithMetadata.setOnClickListener(view15 -> {
            Uri geo = Uri.parse("geo:41.1579438,-8.6291053");
            Intent geoIntent = new Intent(Intent.ACTION_VIEW, geo);

            // add metadata as a bundle, make sure data is serializable
            Bundle journeyMetadata = new Bundle();
            journeyMetadata.putString("my_internal_id", "aaabbbccc");
            journeyMetadata.putString("my_internal_id_2", "cccdddeee");
            journeyMetadata.putBoolean("my_is_active", true);
            journeyMetadata.putFloat("my_float", 123.1f);
            journeyMetadata.putInt("my_int", 123);

            // reserved key journey_metadata to store bundle. The journey will be created with it
            // and can be retrieved later
            geoIntent.putExtra("journey_metadata", journeyMetadata);
            try {
                startActivity(geoIntent);
            }catch (ActivityNotFoundException ignore){

            }
        });

        binding.noPermissionWorkspace.setOnClickListener(view15 -> {
            Uri task = Uri.parse(String.format("show_task:?workspace_id=%s,task_id=%s", unknownWorkspace, unknownTask));
            Intent taskIntent = new Intent("com.meight.driver.SHOW_TASK", task);
            try {
                startActivity(taskIntent);
            }catch (ActivityNotFoundException ignore){

            }
        });

        binding.taskNotFound.setOnClickListener(view1 -> {
            Uri task = Uri.parse(String.format("show_task:?workspace_id=%s,task_id=%s", knownWorkspace, unknownTask));
            Intent taskIntent = new Intent("com.meight.driver.SHOW_TASK", task);
            try {
                startActivity(taskIntent);
            }catch (ActivityNotFoundException ignore){

            }
        });

        binding.noWorkspace.setOnClickListener(view12 -> {
            Uri task = Uri.parse(String.format("show_task:?workspace_id=%s,task_id=%s", unknownWorkspace, knownTask));
            Intent taskIntent = new Intent("com.meight.driver.SHOW_TASK", task);
            try {
                startActivity(taskIntent);
            }catch (ActivityNotFoundException ignore){

            }
        });

        binding.noTask.setOnClickListener(view13 -> {
            Uri task = Uri.parse(String.format("show_task:?workspace_id=%s", unknownWorkspace));
            Intent taskIntent = new Intent("com.meight.driver.SHOW_TASK", task);
            try {
                startActivity(taskIntent);
            }catch (ActivityNotFoundException ignore){

            }
        });

        binding.taskFound.setOnClickListener(view14 -> {
            Uri task = Uri.parse(String.format("show_task:?workspace_id=%s,task_id=%s", knownWorkspace, knownTask));
            Intent taskIntent = new Intent("com.meight.driver.SHOW_TASK", task);
            try {
                startActivity(taskIntent);
            }catch (ActivityNotFoundException ignore){

            }
        });

        binding.taskAlreadyFinished.setOnClickListener(view14 -> {
            Uri task = Uri.parse(String.format("show_task:?workspace_id=%s,task_id=%s", knownWorkspace, finishedTask));
            Intent taskIntent = new Intent("com.meight.driver.SHOW_TASK", task);
            try {
                startActivity(taskIntent);
            }catch (ActivityNotFoundException ignore){

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}