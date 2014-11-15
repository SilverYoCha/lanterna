/*
 * This file is part of lanterna (http://code.google.com/p/lanterna/).
 * 
 * lanterna is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Copyright (C) 2010-2014 Martin
 */
package com.googlecode.lanterna.gui2;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;

/**
 *
 * @author Martin
 */
public interface Component extends TextGUIElement {
    /**
     * Returns the top-left corner of this component, measured from its parent.
     * @return Position of this component
     */
    TerminalPosition getPosition();

    /**
     * This method will be called by the layout manager when it has decided where the component is to be located. If you
     * call this method yourself, prepare for unexpected results.
     * @param position Top-left position of the component, relative to its parent
     * @return Itself
     */
    Component setPosition(TerminalPosition position);

    /**
     * Returns how large this component is. If the layout manager has not yet laid this component out, it will return
     * an empty size (0x0)
     * @return How large this component is
     */
    TerminalSize getSize();

    /**
     * This method will be called by the layout manager when it has decided how large the component will be. If you call
     * this method yourself, prepare for unexpected results.
     * @param size Current size of the component
     * @return Itself
     */
    Component setSize(TerminalSize size);

    /**
     * Returns the ideal size this component would like to have, in order to draw itself properly. There are no
     * guarantees the GUI system will decide to give it this size though.
     * @return Size we would like to be
     */
    TerminalSize getPreferredSize();


    /**
     * Overrides the components preferred size calculation and makes the {@code getPreferredSize()} always return the
     * value passed in here. If you call this will {@code null}, it will re-enable the preferred size calculation again.
     * Please note that using this method on components that are not designed to work with arbitrary sizes make have
     * unexpected behaviour.
     * @param explicitPreferredSize Preferred size we want to use for this component
     * @return Itself
     */
    Component setPreferredSize(TerminalSize explicitPreferredSize);

    /**
     * Sets optional layout data associated with this component. This meaning of this data is up to the layout manager
     * to figure out, see each layout manager for examples of how to use it.
     * @param data Layout data associated with this component
     * @return Itself
     */
    Component setLayoutData(Object data);

    /**
     * Returns the layout data associated with this component. This data will optionally be used by the layout manager,
     * see the documentation for each layout manager for more details on valid values and their meaning.
     * @return This component's layout data
     */
    Object getLayoutData();
    
    /**
     * Returns the container which is holding this container, or {@code null} if it's not assigned to anything.
     * @return Parent container or null
     */
    Container getParent();
    
    /**
     * Returns the TextGUI that this component is currently part of. If the component hasn't been added to any container
     * or in any other way placed into a GUI system, this method will return null.
     * @return The TextGUI that this component belongs to, or null if none
     */
    TextGUI getTextGUI();
    
    /**
     * Returns true if this component is inside of the specified Container. It might be a direct child or not, this 
     * method makes no difference. If {@code getParent()} is not the same instance as {@code container}, but if this 
     * method returns true, you can be sure that this component is not a direct child.
     * @param container Container to test if this component is inside
     * @return True if this component is contained in some way within the {@code container}
     */
    boolean isInside(Container container);
    
    /**
     * Takes a border object and moves this component inside it and then returns it again. This makes it easy to quickly
     * wrap a component on creation, like this:
     * <pre>
     * container.addComponent(new Button("Test").withBorder(Borders.singleLine()));
     * </pre>
     * @param border
     * @return 
     */
    Border withBorder(Border border);
    
    /**
     * Translates a position local to the container to the base pane's coordinate space. For a window-based GUI, this 
     * be a coordinate in the window's coordinate space.
     * @param position Position to translate (relative to the container's top-left corner)
     * @return Position in base pane space
     */
    TerminalPosition toBasePane(TerminalPosition position);

    /**
     * Returns the BasePane that this container belongs to. In a window-based GUI system, this will be a Window.
     * @return The base pane this component is placed on, or {@code null} if none
     */
    BasePane getBasePane();
    
    /**
     * Called by the GUI system when you add a component to a container; DO NOT CALL THIS YOURSELF! 
     * @param container Container that this component was just added to
     */
    void onAdded(Container container);
    
    /**
     * Called by the GUI system when you remove a component from a container; DO NOT CALL THIS YOURSELF! 
     * @param container Container that this component was just removed from
     */
    void onRemoved(Container container);
}
