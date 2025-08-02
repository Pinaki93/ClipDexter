# Screenshot Guide for ClipDexter

This guide provides instructions for capturing high-quality screenshots of the ClipDexter application for use in the README.

## 📸 Screenshot Requirements

### Image Specifications
- **Format**: PNG (preferred) or JPEG
- **Resolution**: 1200x800 pixels minimum
- **Quality**: High quality, crisp text
- **File Size**: Under 500KB per image
- **Aspect Ratio**: 3:2 or 4:3 recommended

### Content Guidelines
- **Clean Interface**: No personal data or sensitive information
- **Good Lighting**: Well-lit screenshots with good contrast
- **Focused Content**: Highlight the main features being demonstrated
- **Consistent Style**: Use similar window sizes and layouts

## 🎯 Required Screenshots

### 1. Main List Screen (`main-list-screen.png`)
**Purpose**: Show the main interface with the list of Python scripts

**What to Capture**:
- Main window showing the list of utilities
- At least 3-4 visible script cards
- "Run" and "Edit" buttons visible
- Clean, uncluttered interface

**Recommended Setup**:
- Window size: 800x600 pixels
- Show utilities like "Convert to Uppercase", "Extract URLs", etc.
- Ensure good contrast and readability

### 2. Script Editor (`detail-edit-screen.png`)
**Purpose**: Demonstrate the Python script editing functionality

**What to Capture**:
- Script editor interface
- Python code visible in the editor
- "Save Changes" and "Cancel" buttons
- Syntax highlighting (if available)

**Recommended Setup**:
- Window size: 800x600 pixels
- Show a simple Python script (e.g., uppercase conversion)
- Ensure code is readable and well-formatted

### 3. Compact View (`compact-view.png`)
**Purpose**: Show the clean, modern Material 3 design

**What to Capture**:
- Smaller window showing the interface
- Material 3 design elements
- Clean, modern appearance

**Recommended Setup**:
- Window size: 600x450 pixels
- Focus on design elements and layout

## 🛠️ Capture Instructions

### macOS
```bash
# Manual capture
screencapture -R x,y,width,height filename.png

# Interactive capture
screencapture -i filename.png

# Capture specific window
screencapture -W filename.png
```

### Windows
```bash
# Using Snipping Tool or built-in screenshot tools
# Or use: Win + Shift + S for screen snip
```

### Linux
```bash
# Using gnome-screenshot or similar tools
gnome-screenshot -a -f filename.png
```

## 📁 File Organization

```
docs/
└── images/
    ├── main-list-screen.png
    ├── detail-edit-screen.png
    └── compact-view.png
```

## 🎨 Optimization Tips

### Before Capture
1. **Clean Desktop**: Remove unnecessary icons and files
2. **Good Lighting**: Ensure screen is well-lit
3. **High Contrast**: Use dark mode or light mode consistently
4. **Focus**: Highlight the main features

### After Capture
1. **Crop**: Remove unnecessary borders and UI elements
2. **Resize**: Optimize for web display (1200x800 recommended)
3. **Compress**: Reduce file size while maintaining quality
4. **Test**: Ensure images look good on GitHub

### Tools for Optimization
- **ImageOptim** (macOS) - Automatic compression
- **TinyPNG** (Web) - Online compression
- **GIMP** (Cross-platform) - Advanced editing
- **Preview** (macOS) - Basic editing and resizing

## 📝 README Integration

### Markdown Syntax
```markdown
![Alt Text](docs/images/filename.png)
*Caption describing the screenshot*
```

### Best Practices
1. **Descriptive Alt Text**: Use meaningful descriptions
2. **Clear Captions**: Explain what the screenshot shows
3. **Consistent Sizing**: Use similar dimensions for all screenshots
4. **Logical Order**: Place screenshots in order of user workflow

## 🔄 Update Process

1. **Capture** new screenshots following the guidelines
2. **Optimize** images for web display
3. **Replace** existing images in `docs/images/`
4. **Update** README if needed
5. **Test** appearance on GitHub
6. **Commit** and push changes

## 🚫 What to Avoid

- **Personal Data**: Never include personal information
- **Low Quality**: Avoid blurry or pixelated images
- **Large Files**: Keep images under 500KB
- **Inconsistent Style**: Maintain consistent window sizes and layouts
- **Cluttered Interface**: Keep screenshots clean and focused

## ✅ Quality Checklist

Before committing screenshots, ensure:
- [ ] Images are high quality and crisp
- [ ] Text is readable
- [ ] File sizes are optimized
- [ ] No personal data is visible
- [ ] Screenshots follow consistent style
- [ ] Alt text and captions are descriptive
- [ ] Images work well on GitHub

---

**Remember**: Good screenshots can significantly improve the user experience and make your project more attractive to potential users! 📸✨ 