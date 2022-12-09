package com.example.asthmaapplication.main.utils;
import android.animation.Animator;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;

public class ActivityUtils {
    public static void addFragment(FragmentManager fragmentManager,
                                   Fragment fragment,
                                   int frameId) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(frameId, fragment);
        ft.commit();
    }

    /**
     * Creates a fresh stack of fragments using tag as the "name" of the root fragment
     *
     * @param fragmentManager
     * @param fragment        that will be the "root" of the stack
     * @param frameId
     * @param tag             root name
     */
    public static void addFragmentWithBackStack(FragmentManager fragmentManager,
                                                Fragment fragment,
                                                int frameId,
                                                String tag) {
        fragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentManager.beginTransaction()
                .replace(frameId, fragment)
                .addToBackStack(tag)
                .commit();

    }

    /**
     * Use in conjunction of ActivityUtils.addFragmentWithBackStack with a current
     * root fragment, you can add more fragments to the stack with this
     *
     * @param fragmentManager
     * @param fragment
     * @param frameId
     */
    public static void addFragmentOnTop(FragmentManager fragmentManager,
                                        Fragment fragment,
                                        int frameId) {
        fragmentManager.beginTransaction()
                .replace(frameId, fragment)
                .addToBackStack(null)
                .commit();

    }


    public static void runLayoutAnimation(final RecyclerView recyclerView, LayoutAnimationController controller) {
        final Context context = recyclerView.getContext();

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public static void showKeyboard(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    public static void hideKeyboard(@NotNull Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } else {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }
    }

    public static void showKeyboard(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void uppercaseEditText(EditText target, String text, TextWatcher watcher) {
        int selection = target.getSelectionStart();
        target.removeTextChangedListener(watcher);
        target.setText(text.toUpperCase());
        target.setSelection(selection);
        target.addTextChangedListener(watcher);
    }

    public static void fadeView(View view, boolean show) {
        float startAlpha = show ? 0f : 1f;
        float endAlpha = show ? 1f : 0f;
        long animationDuration = 500;

        Animator.AnimatorListener listener = null;

        if (show) {
            view.setVisibility(View.VISIBLE);
        } else {
            listener = new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    view.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            };

        }
        view.setAlpha(startAlpha);
        view.animate()
                .alpha(endAlpha)
                .setDuration(animationDuration)
                .setListener(listener);
    }

}