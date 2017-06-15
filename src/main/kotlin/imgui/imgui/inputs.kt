package imgui.imgui

import imgui.Context as g
import imgui.IO
import imgui.Key_


//IMGUI_API int           GetKeyIndex(ImGuiKey key);                                          // map ImGuiKey_* values into user's key index. == io.KeyMap[key]
//IMGUI_API bool          IsKeyDown(int key_index);                                           // key_index into the keys_down[] array, imgui doesn't know the semantic of each entry, uses your own indices!

/** uses user's key indices as stored in the keys_down[] array. if repeat=true. uses io.KeyRepeatDelay / KeyRepeatRate  */
fun isKeyPressed(keyIndex: Key_, repeat: Boolean = true): Boolean {
    val keyIndex = keyIndex.i
    if (keyIndex < 0) return false
    assert(keyIndex >= 0 && keyIndex < IO.keysDown.size)
    val t = IO.keysDownDuration[keyIndex]
    if (t == 0f)
        return true

    if (repeat && t > IO.keyRepeatDelay) {
        val delay = IO.keyRepeatDelay
        val rate = IO.keyRepeatRate
        if ((((t - delay) % rate) > rate * 0.5f) != ((t - delay - IO.deltaTime) % rate) > rate * 0.5f)
            return true
    }
    return false
}

//IMGUI_API bool          IsKeyReleased(int key_index);                                       // "
//IMGUI_API bool          IsMouseDown(int button);                                            // is mouse button held
//IMGUI_API bool          IsMouseClicked(int button, bool repeat = false);                    // did mouse button clicked (went from !Down to Down)
//IMGUI_API bool          IsMouseDoubleClicked(int button);                                   // did mouse button double-clicked. a double-click returns false in IsMouseClicked(). uses io.MouseDoubleClickTime.
//IMGUI_API bool          IsMouseReleased(int button);                                        // did mouse button released (went from Down to !Down)
//IMGUI_API bool          IsMouseHoveringWindow();                                            // is mouse hovering current window ("window" in API names always refer to current window). disregarding of any consideration of being blocked by a popup. (unlike IsWindowHovered() this will return true even if the window is blocked because of a popup)
//IMGUI_API bool          IsMouseHoveringAnyWindow();                                         // is mouse hovering any visible window
//IMGUI_API bool          IsMouseHoveringRect(const ImVec2& r_min, const ImVec2& r_max, bool clip = true);  // is mouse hovering given bounding rect (in screen space). clipped by current clipping settings. disregarding of consideration of focus/window ordering/blocked by a popup.
//IMGUI_API bool          IsMouseDragging(int button = 0, float lock_threshold = -1.0f);      // is mouse dragging. if lock_threshold < -1.0f uses io.MouseDraggingThreshold
//IMGUI_API ImVec2        GetMousePos();                                                      // shortcut to ImGui::GetIO().MousePos provided by user, to be consistent with other calls
//IMGUI_API ImVec2        GetMousePosOnOpeningCurrentPopup();                                 // retrieve backup of mouse positioning at the time of opening popup we have BeginPopup() into
//IMGUI_API ImVec2        GetMouseDragDelta(int button = 0, float lock_threshold = -1.0f);    // dragging amount since clicking. if lock_threshold < -1.0f uses io.MouseDraggingThreshold
//IMGUI_API void          ResetMouseDragDelta(int button = 0);                                //
//IMGUI_API ImGuiMouseCursor GetMouseCursor();                                                // get desired cursor type, reset in ImGui::NewFrame(), this updated during the frame. valid before Render(). If you use software rendering by setting io.MouseDrawCursor ImGui will render those for you
//IMGUI_API void          SetMouseCursor(ImGuiMouseCursor type);                              // set desired cursor type
//IMGUI_API void          CaptureKeyboardFromApp(bool capture = true);                        // manually override io.WantCaptureKeyboard flag next frame (said flag is entirely left for your application handle). e.g. force capture keyboard when your widget is being hovered.
//IMGUI_API void          CaptureMouseFromApp(bool capture = true);                           // manually override io.WantCaptureMouse flag next frame (said flag is entirely left for your application handle).