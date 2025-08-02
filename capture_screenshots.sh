#!/bin/bash

# Screenshot capture script for ClipDexter
# This script captures screenshots of the application for the README

echo "🎯 Capturing ClipDexter screenshots..."

# Wait for app to be ready
sleep 5

# Create screenshots directory if it doesn't exist
mkdir -p docs/images

# Capture main list screen
echo "📸 Capturing main list screen..."
screencapture -R 100,100,800,600 docs/images/main-list-screen.png

# Wait a moment
sleep 2

# Capture detail/edit screen (you'll need to click edit on a utility first)
echo "📸 Capturing detail/edit screen..."
screencapture -R 100,100,800,600 docs/images/detail-edit-screen.png

# Wait a moment
sleep 2

# Capture a smaller version for better README display
echo "📸 Capturing compact version..."
screencapture -R 150,150,600,450 docs/images/compact-view.png

echo "✅ Screenshots captured successfully!"
echo "📁 Check docs/images/ for the captured screenshots" 