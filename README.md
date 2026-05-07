const { Document, Packer, Paragraph, TextRun, Table, TableRow, TableCell, 
        AlignmentType, HeadingLevel, BorderStyle, WidthType, ShadingType,
        TabStopType, TabStopPosition, LevelFormat, PageBreak } = require('docx');
const fs = require('fs');

// Professional color palette
const colors = {
  primary: "2E75B6",      // Professional blue
  secondary: "4472C4",    // Light blue
  accent: "70AD47",       // Green accent
  dark: "44546A",         // Dark gray
  light: "E7E6E6",        // Light gray
  white: "FFFFFF",
  headerBg: "2E75B6",     // Deep blue
  tableHeader: "4472C4",  // Medium blue
  codeBlock: "F2F2F2",    // Light gray
  warning: "FFC000",      // Orange
  success: "70AD47"       // Green
};

const doc = new Document({
  styles: {
    default: {
      document: {
        run: { font: "Calibri", size: 22 }, // 11pt default
        paragraph: { spacing: { line: 276, after: 140 } }
      }
    },
    paragraphStyles: [
      {
        id: "Heading1",
        name: "Heading 1",
        basedOn: "Normal",
        next: "Normal",
        quickFormat: true,
        run: { size: 36, bold: true, font: "Calibri", color: colors.primary },
        paragraph: { 
          spacing: { before: 480, after: 240 },
          outlineLevel: 0,
          border: {
            bottom: { style: BorderStyle.SINGLE, size: 12, color: colors.primary, space: 4 }
          }
        }
      },
      {
        id: "Heading2",
        name: "Heading 2",
        basedOn: "Normal",
        next: "Normal",
        quickFormat: true,
        run: { size: 30, bold: true, font: "Calibri", color: colors.secondary },
        paragraph: { spacing: { before: 360, after: 180 }, outlineLevel: 1 }
      },
      {
        id: "Heading3",
        name: "Heading 3",
        basedOn: "Normal",
        next: "Normal",
        quickFormat: true,
        run: { size: 26, bold: true, font: "Calibri", color: colors.dark },
        paragraph: { spacing: { before: 280, after: 140 }, outlineLevel: 2 }
      }
    ]
  },
  numbering: {
    config: [
      {
        reference: "bullets",
        levels: [
          {
            level: 0,
            format: LevelFormat.BULLET,
            text: "●",
            alignment: AlignmentType.LEFT,
            style: {
              paragraph: { indent: { left: 720, hanging: 360 } }
            }
          }
        ]
      },
      {
        reference: "checkmarks",
        levels: [
          {
            level: 0,
            format: LevelFormat.BULLET,
            text: "✓",
            alignment: AlignmentType.LEFT,
            style: {
              run: { color: colors.success, bold: true },
              paragraph: { indent: { left: 720, hanging: 360 } }
            }
          }
        ]
      }
    ]
  },
  sections: [{
    properties: {
      page: {
        size: { width: 12240, height: 15840 },
        margin: { top: 1440, right: 1440, bottom: 1440, left: 1440 }
      }
    },
    children: [
      // ========== COVER PAGE ==========
      new Paragraph({
        alignment: AlignmentType.CENTER,
        spacing: { before: 2880 },
        children: [
          new TextRun({
            text: "📱",
            size: 96
          })
        ]
      }),
      
      new Paragraph({
        alignment: AlignmentType.CENTER,
        spacing: { before: 240, after: 120 },
        children: [
          new TextRun({
            text: "Call Log Management System",
            size: 48,
            bold: true,
            color: colors.primary,
            font: "Calibri"
          })
        ]
      }),

      new Paragraph({
        alignment: AlignmentType.CENTER,
        spacing: { after: 480 },
        children: [
          new TextRun({
            text: "A Professional Swing-Based Call Log Management Application",
            size: 26,
            color: colors.dark,
            font: "Calibri"
          })
        ]
      }),

      // Feature badges
      new Table({
        width: { size: 7000, type: WidthType.DXA },
        alignment: AlignmentType.CENTER,
        borders: {
          top: { style: BorderStyle.NONE, size: 0 },
          bottom: { style: BorderStyle.NONE, size: 0 },
          left: { style: BorderStyle.NONE, size: 0 },
          right: { style: BorderStyle.NONE, size: 0 },
          insideHorizontal: { style: BorderStyle.NONE, size: 0 },
          insideVertical: { style: BorderStyle.NONE, size: 0 }
        },
        rows: [
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3500, type: WidthType.DXA },
                borders: {
                  top: { style: BorderStyle.NONE, size: 0 },
                  bottom: { style: BorderStyle.NONE, size: 0 },
                  left: { style: BorderStyle.NONE, size: 0 },
                  right: { style: BorderStyle.NONE, size: 0 }
                },
                shading: { fill: colors.tableHeader, type: ShadingType.CLEAR },
                margins: { top: 120, bottom: 120, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    alignment: AlignmentType.CENTER,
                    children: [
                      new TextRun({ text: "🎨 Professional GUI", color: colors.white, bold: true, size: 22 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 3500, type: WidthType.DXA },
                borders: {
                  top: { style: BorderStyle.NONE, size: 0 },
                  bottom: { style: BorderStyle.NONE, size: 0 },
                  left: { style: BorderStyle.NONE, size: 0 },
                  right: { style: BorderStyle.NONE, size: 0 }
                },
                shading: { fill: colors.accent, type: ShadingType.CLEAR },
                margins: { top: 120, bottom: 120, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    alignment: AlignmentType.CENTER,
                    children: [
                      new TextRun({ text: "🏗️ MVC Architecture", color: colors.white, bold: true, size: 22 })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3500, type: WidthType.DXA },
                borders: {
                  top: { style: BorderStyle.NONE, size: 0 },
                  bottom: { style: BorderStyle.NONE, size: 0 },
                  left: { style: BorderStyle.NONE, size: 0 },
                  right: { style: BorderStyle.NONE, size: 0 }
                },
                shading: { fill: colors.primary, type: ShadingType.CLEAR },
                margins: { top: 120, bottom: 120, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    alignment: AlignmentType.CENTER,
                    children: [
                      new TextRun({ text: "📊 Real-time Statistics", color: colors.white, bold: true, size: 22 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 3500, type: WidthType.DXA },
                borders: {
                  top: { style: BorderStyle.NONE, size: 0 },
                  bottom: { style: BorderStyle.NONE, size: 0 },
                  left: { style: BorderStyle.NONE, size: 0 },
                  right: { style: BorderStyle.NONE, size: 0 }
                },
                shading: { fill: colors.secondary, type: ShadingType.CLEAR },
                margins: { top: 120, bottom: 120, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    alignment: AlignmentType.CENTER,
                    children: [
                      new TextRun({ text: "🔍 Advanced Search", color: colors.white, bold: true, size: 22 })
                    ]
                  })
                ]
              })
            ]
          })
        ]
      }),

      new Paragraph({
        alignment: AlignmentType.CENTER,
        spacing: { before: 960, after: 240 },
        children: [
          new TextRun({
            text: "Advanced Java implementation demonstrating",
            size: 22,
            color: colors.dark
          })
        ]
      }),
      new Paragraph({
        alignment: AlignmentType.CENTER,
        children: [
          new TextRun({
            text: "modern GUI development and design patterns",
            size: 22,
            color: colors.dark
          })
        ]
      }),

      new Paragraph({
        alignment: AlignmentType.CENTER,
        spacing: { before: 1440 },
        children: [
          new TextRun({
            text: "Last Updated: May 2026",
            size: 20,
            italics: true,
            color: colors.dark
          })
        ]
      }),

      new Paragraph({
        alignment: AlignmentType.CENTER,
        children: [
          new TextRun({
            text: "Java Version: 8+  •  Framework: Swing  •  Pattern: MVC",
            size: 20,
            italics: true,
            color: colors.dark
          })
        ]
      }),

      new Paragraph({
        children: [new PageBreak()]
      }),

      // ========== TABLE OF CONTENTS ==========
      new Paragraph({
        heading: HeadingLevel.HEADING_1,
        children: [new TextRun("📋 Table of Contents")]
      }),

      new Paragraph({
        spacing: { after: 80 },
        children: [
          new TextRun({ text: "1. Project Overview", size: 22 })
        ]
      }),
      new Paragraph({
        spacing: { after: 80 },
        children: [
          new TextRun({ text: "2. Project Structure", size: 22 })
        ]
      }),
      new Paragraph({
        spacing: { after: 80 },
        children: [
          new TextRun({ text: "3. Features & Capabilities", size: 22 })
        ]
      }),
      new Paragraph({
        spacing: { after: 80 },
        children: [
          new TextRun({ text: "4. Technology Stack", size: 22 })
        ]
      }),
      new Paragraph({
        spacing: { after: 80 },
        children: [
          new TextRun({ text: "5. Architecture & Design Patterns", size: 22 })
        ]
      }),
      new Paragraph({
        spacing: { after: 80 },
        children: [
          new TextRun({ text: "6. Getting Started", size: 22 })
        ]
      }),
      new Paragraph({
        spacing: { after: 80 },
        children: [
          new TextRun({ text: "7. Usage Guide", size: 22 })
        ]
      }),
      new Paragraph({
        spacing: { after: 80 },
        children: [
          new TextRun({ text: "8. Advanced Java Concepts", size: 22 })
        ]
      }),
      new Paragraph({
        spacing: { after: 80 },
        children: [
          new TextRun({ text: "9. Customization Guide", size: 22 })
        ]
      }),

      new Paragraph({
        children: [new PageBreak()]
      }),

      // ========== PROJECT OVERVIEW ==========
      new Paragraph({
        heading: HeadingLevel.HEADING_1,
        children: [new TextRun("🎯 Project Overview")]
      }),

      new Paragraph({
        children: [
          new TextRun({
            text: "The ",
            size: 22
          }),
          new TextRun({
            text: "Call Log Management System",
            size: 22,
            bold: true,
            color: colors.primary
          }),
          new TextRun({
            text: " is a feature-rich desktop application built with Java Swing that allows users to manage, view, and analyze their call history. The application showcases advanced Java concepts, professional UI/UX design, and enterprise-level architecture patterns.",
            size: 22
          })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        children: [new TextRun("Key Highlights")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Professional Swing GUI", bold: true }),
          new TextRun({ text: " with custom rendering and modern design patterns" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "MVC Architecture", bold: true }),
          new TextRun({ text: " ensuring clean separation of concerns" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Real-time Statistics", bold: true }),
          new TextRun({ text: " with dynamic pie chart visualization" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Advanced Search & Filtering", bold: true }),
          new TextRun({ text: " with live updates as you type" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Type-Safe Collections", bold: true }),
          new TextRun({ text: " leveraging modern Java practices" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Custom Component Rendering", bold: true }),
          new TextRun({ text: " with intuitive color-coded cells" })
        ]
      }),

      new Paragraph({
        children: [new PageBreak()]
      }),

      // ========== PROJECT STRUCTURE ==========
      new Paragraph({
        heading: HeadingLevel.HEADING_1,
        children: [new TextRun("📁 Project Structure")]
      }),

      new Paragraph({
        children: [
          new TextRun({
            text: "The project follows a clean, modular architecture with well-defined packages and responsibilities:",
            size: 22
          })
        ]
      }),

      // Structure table
      new Table({
        width: { size: 9360, type: WidthType.DXA },
        columnWidths: [3120, 6240],
        rows: [
          // Header row
          new TableRow({
            tableHeader: true,
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                shading: { fill: colors.tableHeader, type: ShadingType.CLEAR },
                margins: { top: 120, bottom: 120, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Package/File", bold: true, color: colors.white, size: 24 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                shading: { fill: colors.tableHeader, type: ShadingType.CLEAR },
                margins: { top: 120, bottom: 120, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Description", bold: true, color: colors.white, size: 24 })
                    ]
                  })
                ]
              })
            ]
          }),
          // main package
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                shading: { fill: colors.codeBlock, type: ShadingType.CLEAR },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "main/", bold: true, font: "Consolas" })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Application entry points" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 360, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Main.java", font: "Consolas", size: 20 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Console-based entry point" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 360, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "UIMain.java", font: "Consolas", size: 20 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Swing GUI entry point (recommended)" })
                    ]
                  })
                ]
              })
            ]
          }),
          // model package
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                shading: { fill: colors.codeBlock, type: ShadingType.CLEAR },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "model/", bold: true, font: "Consolas" })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Data models and POJOs" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 360, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Call.java", font: "Consolas", size: 20 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Call data model with phone number, duration, type, timestamp" })
                    ]
                  })
                ]
              })
            ]
          }),
          // service package
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                shading: { fill: colors.codeBlock, type: ShadingType.CLEAR },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "service/", bold: true, font: "Consolas" })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Business logic and data management" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 360, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "CallLogManager.java", font: "Consolas", size: 20 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Call storage and CRUD operations" })
                    ]
                  })
                ]
              })
            ]
          }),
          // ui package
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                shading: { fill: colors.codeBlock, type: ShadingType.CLEAR },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "ui/", bold: true, font: "Consolas" })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "User interface components and controllers" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 360, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "CallLogUI.java", font: "Consolas", size: 20 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Main window and UI layout orchestration" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 360, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "CallLogController.java", font: "Consolas", size: 20 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "MVC controller with business logic" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 360, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "CallStatisticsPanel.java", font: "Consolas", size: 20 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Statistics visualization with Graphics2D pie chart" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 360, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "SearchFilterPanel.java", font: "Consolas", size: 20 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Real-time search and filtering interface" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 360, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "CallTypeRenderer.java", font: "Consolas", size: 20 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Custom table cell renderer with color coding" })
                    ]
                  })
                ]
              })
            ]
          }),
          // util package
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                shading: { fill: colors.codeBlock, type: ShadingType.CLEAR },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "util/", bold: true, font: "Consolas" })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Utility classes and enumerations" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 360, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "CallType.java", font: "Consolas", size: 20 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 80, bottom: 80, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Enum defining call types (INCOMING, OUTGOING, MISSED)" })
                    ]
                  })
                ]
              })
            ]
          })
        ]
      }),

      new Paragraph({
        children: [new PageBreak()]
      }),

      // ========== FEATURES ==========
      new Paragraph({
        heading: HeadingLevel.HEADING_1,
        children: [new TextRun("✨ Features & Capabilities")]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        children: [new TextRun("Core Features")]
      }),

      // Core features table
      new Table({
        width: { size: 9360, type: WidthType.DXA },
        columnWidths: [3120, 6240],
        rows: [
          new TableRow({
            tableHeader: true,
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                shading: { fill: colors.accent, type: ShadingType.CLEAR },
                margins: { top: 120, bottom: 120, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Feature", bold: true, color: colors.white, size: 24 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                shading: { fill: colors.accent, type: ShadingType.CLEAR },
                margins: { top: 120, bottom: 120, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Description", bold: true, color: colors.white, size: 24 })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Add Calls", bold: true })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Create new call entries with phone number, duration, type, and timestamp" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                shading: { fill: colors.light, type: ShadingType.CLEAR },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "View All Calls", bold: true })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                shading: { fill: colors.light, type: ShadingType.CLEAR },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Display complete call history in a sortable, color-coded table" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Filter by Type", bold: true })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "View only Incoming, Outgoing, or Missed calls with one click" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                shading: { fill: colors.light, type: ShadingType.CLEAR },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Delete Calls", bold: true })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                shading: { fill: colors.light, type: ShadingType.CLEAR },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Remove individual calls from the history" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Clear History", bold: true })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Remove all calls at once with confirmation" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                shading: { fill: colors.light, type: ShadingType.CLEAR },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Real-time Statistics", bold: true })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                shading: { fill: colors.light, type: ShadingType.CLEAR },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Live count updates for call types with visual charts" })
                    ]
                  })
                ]
              })
            ]
          })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 360 },
        children: [new TextRun("Advanced UI Features")]
      }),

      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Color-Coded Cells: ", bold: true }),
          new TextRun({ text: "Automatically colored by call type (Green for incoming, Orange for outgoing, Red for missed)" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Pie Chart Analytics: ", bold: true }),
          new TextRun({ text: "Visual distribution of call types with custom Graphics2D rendering" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Menu-Driven Interface: ", bold: true }),
          new TextRun({ text: "Comprehensive File, Call, and View menus for all operations" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Search Panel: ", bold: true }),
          new TextRun({ text: "Filter calls by phone number with instant, live results" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Responsive Layout: ", bold: true }),
          new TextRun({ text: "Adaptable to different window sizes with proper component resizing" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Custom Button Styling: ", bold: true }),
          new TextRun({ text: "Intuitive color-coded action buttons for visual clarity" })
        ]
      }),

      new Paragraph({
        children: [new PageBreak()]
      }),

      // ========== TECHNOLOGY STACK ==========
      new Paragraph({
        heading: HeadingLevel.HEADING_1,
        children: [new TextRun("🛠️ Technology Stack")]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        children: [new TextRun("Language & Framework")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Java 8+", bold: true }),
          new TextRun({ text: " - Core programming language with modern features" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Swing Framework", bold: true }),
          new TextRun({ text: " - Professional desktop GUI toolkit" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_3,
        spacing: { before: 240 },
        children: [new TextRun("Key Components")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "JFrame, JPanel, JTable - Core container and display components" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "JDialog, JMenuBar - Dialog boxes and menu systems" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Layout Managers - BorderLayout, GridLayout, FlowLayout for flexible positioning" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 360 },
        children: [new TextRun("Design Patterns")]
      }),

      new Table({
        width: { size: 9360, type: WidthType.DXA },
        columnWidths: [3120, 6240],
        rows: [
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                shading: { fill: colors.primary, type: ShadingType.CLEAR },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "MVC Pattern", bold: true, color: colors.white })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Clean separation of Model, View, and Controller layers" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                shading: { fill: colors.secondary, type: ShadingType.CLEAR },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Observer Pattern", bold: true, color: colors.white })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Event-driven programming with ActionListener implementations" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                shading: { fill: colors.accent, type: ShadingType.CLEAR },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Strategy Pattern", bold: true, color: colors.white })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Custom cell rendering strategies for table visualization" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 3120, type: WidthType.DXA },
                shading: { fill: colors.dark, type: ShadingType.CLEAR },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Utility Pattern", bold: true, color: colors.white })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 6240, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Helper classes for common operations and data manipulation" })
                    ]
                  })
                ]
              })
            ]
          })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 360 },
        children: [new TextRun("Advanced Java Concepts")]
      }),

      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Reflection API - Dynamic field access and runtime type inspection" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Collections Framework - Type-safe ArrayList<T> and DefaultTableModel" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Event Handling - ActionListener and DocumentListener implementations" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Graphics2D - Custom painting and visual rendering" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Thread Safety - SwingUtilities.invokeLater() for EDT compliance" })
        ]
      }),

      new Paragraph({
        children: [new PageBreak()]
      }),

      // ========== ARCHITECTURE ==========
      new Paragraph({
        heading: HeadingLevel.HEADING_1,
        children: [new TextRun("🏗️ Architecture & Design Patterns")]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        children: [new TextRun("MVC Pattern Implementation")]
      }),

      new Paragraph({
        children: [
          new TextRun({
            text: "The application follows a strict Model-View-Controller architecture, ensuring clean separation of concerns and maintainability:",
            size: 22
          })
        ]
      }),

      // MVC layers table
      new Table({
        width: { size: 9360, type: WidthType.DXA },
        columnWidths: [2340, 7020],
        rows: [
          new TableRow({
            children: [
              new TableCell({
                width: { size: 2340, type: WidthType.DXA },
                shading: { fill: colors.accent, type: ShadingType.CLEAR },
                margins: { top: 120, bottom: 120, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "MODEL", bold: true, color: colors.white, size: 26 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 7020, type: WidthType.DXA },
                margins: { top: 120, bottom: 120, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Data Layer - Call.java, CallLogManager.java, CallType.java" })
                    ]
                  }),
                  new Paragraph({
                    spacing: { before: 80 },
                    children: [
                      new TextRun({ text: "Responsible for: ", italics: true }),
                      new TextRun({ text: "Data representation, storage, and business rules" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 2340, type: WidthType.DXA },
                shading: { fill: colors.primary, type: ShadingType.CLEAR },
                margins: { top: 120, bottom: 120, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "VIEW", bold: true, color: colors.white, size: 26 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 7020, type: WidthType.DXA },
                margins: { top: 120, bottom: 120, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Presentation Layer - CallLogUI.java, CallStatisticsPanel.java, SearchFilterPanel.java, CallTypeRenderer.java" })
                    ]
                  }),
                  new Paragraph({
                    spacing: { before: 80 },
                    children: [
                      new TextRun({ text: "Responsible for: ", italics: true }),
                      new TextRun({ text: "User interface rendering and visual presentation" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 2340, type: WidthType.DXA },
                shading: { fill: colors.secondary, type: ShadingType.CLEAR },
                margins: { top: 120, bottom: 120, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "CONTROLLER", bold: true, color: colors.white, size: 26 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 7020, type: WidthType.DXA },
                margins: { top: 120, bottom: 120, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Logic Layer - CallLogController.java" })
                    ]
                  }),
                  new Paragraph({
                    spacing: { before: 80 },
                    children: [
                      new TextRun({ text: "Responsible for: ", italics: true }),
                      new TextRun({ text: "Coordination between Model and View, event handling, statistics calculation" })
                    ]
                  })
                ]
              })
            ]
          })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 360 },
        children: [new TextRun("Component Responsibilities")]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_3,
        children: [new TextRun("Model Layer")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Call.java: ", bold: true }),
          new TextRun({ text: "Represents a single call with phone number, duration, type, and timestamp" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "CallLogManager.java: ", bold: true }),
          new TextRun({ text: "Manages collection of calls and provides CRUD operations" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "CallType.java: ", bold: true }),
          new TextRun({ text: "Enum defining call categories (INCOMING, OUTGOING, MISSED)" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_3,
        spacing: { before: 240 },
        children: [new TextRun("View Layer")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "CallLogUI.java: ", bold: true }),
          new TextRun({ text: "Main window container and layout orchestration" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "CallStatisticsPanel.java: ", bold: true }),
          new TextRun({ text: "Custom graphics rendering for statistics visualization" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "SearchFilterPanel.java: ", bold: true }),
          new TextRun({ text: "Real-time search and filtering interface" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "CallTypeRenderer.java: ", bold: true }),
          new TextRun({ text: "Custom table cell coloring based on call type" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_3,
        spacing: { before: 240 },
        children: [new TextRun("Controller Layer")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "CallLogController.java: ", bold: true }),
          new TextRun({ text: "Bridges UI and data layers, handles statistics calculation, manages filtering and deletion operations" })
        ]
      }),

      new Paragraph({
        children: [new PageBreak()]
      }),

      // ========== GETTING STARTED ==========
      new Paragraph({
        heading: HeadingLevel.HEADING_1,
        children: [new TextRun("🚀 Getting Started")]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        children: [new TextRun("Prerequisites")]
      }),

      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Java 8 or higher installed on your system" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Compiled .class files in bin/ directory (or compile from source)" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 360 },
        children: [new TextRun("Running the Application")]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_3,
        children: [new TextRun("Option 1: GUI Mode (Recommended)")]
      }),

      // Code block simulation
      new Table({
        width: { size: 9360, type: WidthType.DXA },
        columnWidths: [9360],
        rows: [
          new TableRow({
            children: [
              new TableCell({
                width: { size: 9360, type: WidthType.DXA },
                shading: { fill: colors.codeBlock, type: ShadingType.CLEAR },
                margins: { top: 160, bottom: 160, left: 240, right: 240 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "# Navigate to project directory", font: "Consolas", size: 20, color: "008000" })
                    ]
                  }),
                  new Paragraph({
                    children: [
                      new TextRun({ text: 'cd "d:\\6 th sem\\java project"', font: "Consolas", size: 20 })
                    ]
                  }),
                  new Paragraph({
                    spacing: { before: 240 },
                    children: [
                      new TextRun({ text: "# Run the Swing GUI", font: "Consolas", size: 20, color: "008000" })
                    ]
                  }),
                  new Paragraph({
                    children: [
                      new TextRun({ text: "java -cp bin com.calllog.main.UIMain", font: "Consolas", size: 20 })
                    ]
                  })
                ]
              })
            ]
          })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_3,
        spacing: { before: 360 },
        children: [new TextRun("Option 2: Console Mode")]
      }),

      new Table({
        width: { size: 9360, type: WidthType.DXA },
        columnWidths: [9360],
        rows: [
          new TableRow({
            children: [
              new TableCell({
                width: { size: 9360, type: WidthType.DXA },
                shading: { fill: colors.codeBlock, type: ShadingType.CLEAR },
                margins: { top: 160, bottom: 160, left: 240, right: 240 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "# Run the console-based version", font: "Consolas", size: 20, color: "008000" })
                    ]
                  }),
                  new Paragraph({
                    children: [
                      new TextRun({ text: "java -cp bin com.calllog.main.Main", font: "Consolas", size: 20 })
                    ]
                  })
                ]
              })
            ]
          })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_3,
        spacing: { before: 360 },
        children: [new TextRun("Compiling from Source")]
      }),

      new Paragraph({
        children: [
          new TextRun({
            text: "If you don't have pre-compiled .class files:",
            size: 22
          })
        ]
      }),

      new Table({
        width: { size: 9360, type: WidthType.DXA },
        columnWidths: [9360],
        rows: [
          new TableRow({
            children: [
              new TableCell({
                width: { size: 9360, type: WidthType.DXA },
                shading: { fill: colors.codeBlock, type: ShadingType.CLEAR },
                margins: { top: 160, bottom: 160, left: 240, right: 240 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "# Compile all Java files", font: "Consolas", size: 20, color: "008000" })
                    ]
                  }),
                  new Paragraph({
                    children: [
                      new TextRun({ text: "javac -d bin src/com/calllog/**/*.java", font: "Consolas", size: 20 })
                    ]
                  }),
                  new Paragraph({
                    spacing: { before: 240 },
                    children: [
                      new TextRun({ text: "# Run the GUI", font: "Consolas", size: 20, color: "008000" })
                    ]
                  }),
                  new Paragraph({
                    children: [
                      new TextRun({ text: "java -cp bin com.calllog.main.UIMain", font: "Consolas", size: 20 })
                    ]
                  })
                ]
              })
            ]
          })
        ]
      }),

      new Paragraph({
        children: [new PageBreak()]
      }),

      // ========== USAGE GUIDE ==========
      new Paragraph({
        heading: HeadingLevel.HEADING_1,
        children: [new TextRun("📖 Usage Guide")]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        children: [new TextRun("Main Window Overview")]
      }),

      new Paragraph({
        children: [
          new TextRun({
            text: "The main window consists of four primary sections:",
            size: 22
          })
        ]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Menu Bar: ", bold: true }),
          new TextRun({ text: "File, Call, and View menus for all operations" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Statistics Panel: ", bold: true }),
          new TextRun({ text: "Real-time counters showing total, incoming, outgoing, and missed calls" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Call History Table: ", bold: true }),
          new TextRun({ text: "Color-coded, sortable table displaying all call records" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Action Buttons: ", bold: true }),
          new TextRun({ text: "Quick access buttons for common operations" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 360 },
        children: [new TextRun("Menu Operations")]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_3,
        children: [new TextRun("File Menu")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Exit: ", bold: true }),
          new TextRun({ text: "Close the application" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_3,
        spacing: { before: 240 },
        children: [new TextRun("Call Menu")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Add Call: ", bold: true }),
          new TextRun({ text: "Open dialog to create a new call entry" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_3,
        spacing: { before: 240 },
        children: [new TextRun("View Menu")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "All Calls: ", bold: true }),
          new TextRun({ text: "Display entire call history" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Incoming Calls: ", bold: true }),
          new TextRun({ text: "Filter to show only incoming calls" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Outgoing Calls: ", bold: true }),
          new TextRun({ text: "Filter to show only outgoing calls" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Missed Calls: ", bold: true }),
          new TextRun({ text: "Filter to show only missed calls" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 360 },
        children: [new TextRun("Common Operations")]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_3,
        children: [new TextRun("Adding a Call")]
      }),

      new Paragraph({
        children: [
          new TextRun({ text: "1. ", bold: true }),
          new TextRun({ text: 'Click the "Add Call" button or use Call → Add Call menu' })
        ]
      }),
      new Paragraph({
        children: [
          new TextRun({ text: "2. ", bold: true }),
          new TextRun({ text: "Fill in the following details:" })
        ]
      }),
      new Paragraph({
        spacing: { before: 80, left: 720 },
        children: [
          new TextRun({ text: "• Phone Number: Contact's phone number" })
        ]
      }),
      new Paragraph({
        spacing: { left: 720 },
        children: [
          new TextRun({ text: "• Duration: Call length in seconds" })
        ]
      }),
      new Paragraph({
        spacing: { left: 720 },
        children: [
          new TextRun({ text: "• Type: Select INCOMING, OUTGOING, or MISSED" })
        ]
      }),
      new Paragraph({
        children: [
          new TextRun({ text: "3. ", bold: true }),
          new TextRun({ text: 'Click "Save" to add to history' })
        ]
      }),
      new Paragraph({
        children: [
          new TextRun({ text: "4. ", bold: true }),
          new TextRun({ text: "Statistics update automatically in real-time" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_3,
        spacing: { before: 280 },
        children: [new TextRun("Deleting a Call")]
      }),

      new Paragraph({
        children: [
          new TextRun({ text: "1. ", bold: true }),
          new TextRun({ text: "Select a call from the table (single click on the row)" })
        ]
      }),
      new Paragraph({
        children: [
          new TextRun({ text: "2. ", bold: true }),
          new TextRun({ text: 'Click the "Delete Call" button' })
        ]
      }),
      new Paragraph({
        children: [
          new TextRun({ text: "3. ", bold: true }),
          new TextRun({ text: "Call is removed from history and statistics update automatically" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_3,
        spacing: { before: 280 },
        children: [new TextRun("Searching Calls")]
      }),

      new Paragraph({
        children: [
          new TextRun({ text: "1. ", bold: true }),
          new TextRun({ text: "Use the search box to filter by phone number" })
        ]
      }),
      new Paragraph({
        children: [
          new TextRun({ text: "2. ", bold: true }),
          new TextRun({ text: "Type phone number for live filtering" })
        ]
      }),
      new Paragraph({
        children: [
          new TextRun({ text: "3. ", bold: true }),
          new TextRun({ text: "Results update instantly as you type" })
        ]
      }),

      new Paragraph({
        children: [new PageBreak()]
      }),

      // ========== ADVANCED CONCEPTS ==========
      new Paragraph({
        heading: HeadingLevel.HEADING_1,
        children: [new TextRun("🎓 Advanced Java Concepts")]
      }),

      new Paragraph({
        children: [
          new TextRun({
            text: "This project demonstrates numerous advanced Java programming concepts and best practices:",
            size: 22
          })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        children: [new TextRun("1. Swing Framework Mastery")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Complex UI layouts with multiple nested components" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Event-driven programming with ActionListeners" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Custom painting with Graphics2D for pie charts" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Table models and custom cell renderers" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 280 },
        children: [new TextRun("2. Design Patterns")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "MVC Pattern: ", bold: true }),
          new TextRun({ text: "Complete separation of Model, View, and Controller" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Observer Pattern: ", bold: true }),
          new TextRun({ text: "Event listeners for UI updates" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Strategy Pattern: ", bold: true }),
          new TextRun({ text: "Custom cell rendering strategies" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 280 },
        children: [new TextRun("3. Reflection API")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Dynamic field access using getDeclaredField()" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Runtime type inspection and manipulation" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Private field modification with setAccessible(true)" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 280 },
        children: [new TextRun("4. Collections Framework")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Generic ArrayList<Call> for type safety" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "DefaultTableModel for dynamic table data management" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Enhanced for loops and stream operations" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 280 },
        children: [new TextRun("5. Exception Handling")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Comprehensive try-catch blocks for reflection and user errors" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "User-friendly error dialogs with JOptionPane" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Graceful error recovery mechanisms" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 280 },
        children: [new TextRun("6. Graphics & Custom Rendering")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Custom painting with paintComponent(Graphics g)" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Graphics2D for advanced drawing operations" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Anti-aliasing and rendering hints for smooth graphics" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 280 },
        children: [new TextRun("7. Threading & Concurrency")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "SwingUtilities.invokeLater() for thread-safe UI updates" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Event Dispatch Thread (EDT) compliance" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Responsive GUI during long-running operations" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 280 },
        children: [new TextRun("8. Date & Time API")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "LocalDateTime for precise timestamp storage" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "DateTimeFormatter for user-friendly display" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Time zone aware operations" })
        ]
      }),

      new Paragraph({
        children: [new PageBreak()]
      }),

      // ========== CUSTOMIZATION ==========
      new Paragraph({
        heading: HeadingLevel.HEADING_1,
        children: [new TextRun("🔧 Customization Guide")]
      }),

      new Paragraph({
        children: [
          new TextRun({
            text: "The application is designed to be easily customizable. Here are some common customization scenarios:",
            size: 22
          })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        children: [new TextRun("Changing the Color Scheme")]
      }),

      new Paragraph({
        children: [
          new TextRun({ text: "File: ", bold: true }),
          new TextRun({ text: "src/com/calllog/ui/CallTypeRenderer.java", font: "Consolas" })
        ]
      }),

      new Table({
        width: { size: 9360, type: WidthType.DXA },
        columnWidths: [9360],
        rows: [
          new TableRow({
            children: [
              new TableCell({
                width: { size: 9360, type: WidthType.DXA },
                shading: { fill: colors.codeBlock, type: ShadingType.CLEAR },
                margins: { top: 160, bottom: 160, left: 240, right: 240 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "case INCOMING:", font: "Consolas", size: 20 })
                    ]
                  }),
                  new Paragraph({
                    spacing: { left: 360 },
                    children: [
                      new TextRun({ text: "setBackground(new Color(144, 238, 144));", font: "Consolas", size: 20 })
                    ]
                  }),
                  new Paragraph({
                    spacing: { left: 360 },
                    children: [
                      new TextRun({ text: "// Modify RGB values to change color", font: "Consolas", size: 20, color: "008000" })
                    ]
                  }),
                  new Paragraph({
                    spacing: { left: 360 },
                    children: [
                      new TextRun({ text: "break;", font: "Consolas", size: 20 })
                    ]
                  })
                ]
              })
            ]
          })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 360 },
        children: [new TextRun("Modifying Window Size")]
      }),

      new Paragraph({
        children: [
          new TextRun({ text: "File: ", bold: true }),
          new TextRun({ text: "src/com/calllog/ui/CallLogUI.java", font: "Consolas" })
        ]
      }),

      new Table({
        width: { size: 9360, type: WidthType.DXA },
        columnWidths: [9360],
        rows: [
          new TableRow({
            children: [
              new TableCell({
                width: { size: 9360, type: WidthType.DXA },
                shading: { fill: colors.codeBlock, type: ShadingType.CLEAR },
                margins: { top: 160, bottom: 160, left: 240, right: 240 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "setSize(1000, 600);", font: "Consolas", size: 20 })
                    ]
                  }),
                  new Paragraph({
                    children: [
                      new TextRun({ text: "// Change dimensions (width, height)", font: "Consolas", size: 20, color: "008000" })
                    ]
                  })
                ]
              })
            ]
          })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 360 },
        children: [new TextRun("Adding a New Call Type")]
      }),

      new Paragraph({
        children: [
          new TextRun({ text: "1. ", bold: true }),
          new TextRun({ text: "Add the new type to CallType.java enum" })
        ]
      }),
      new Paragraph({
        children: [
          new TextRun({ text: "2. ", bold: true }),
          new TextRun({ text: "Update statistics calculation in CallLogController.java" })
        ]
      }),
      new Paragraph({
        children: [
          new TextRun({ text: "3. ", bold: true }),
          new TextRun({ text: "Add color case in CallTypeRenderer.java" })
        ]
      }),
      new Paragraph({
        children: [
          new TextRun({ text: "4. ", bold: true }),
          new TextRun({ text: "Update UI labels in CallStatisticsPanel.java" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 360 },
        children: [new TextRun("Customizing Table Columns")]
      }),

      new Paragraph({
        children: [
          new TextRun({ text: "File: ", bold: true }),
          new TextRun({ text: "src/com/calllog/ui/CallLogUI.java", font: "Consolas" })
        ]
      }),

      new Table({
        width: { size: 9360, type: WidthType.DXA },
        columnWidths: [9360],
        rows: [
          new TableRow({
            children: [
              new TableCell({
                width: { size: 9360, type: WidthType.DXA },
                shading: { fill: colors.codeBlock, type: ShadingType.CLEAR },
                margins: { top: 160, bottom: 160, left: 240, right: 240 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ 
                        text: 'String[] columnNames = {', 
                        font: "Consolas", 
                        size: 20 
                      })
                    ]
                  }),
                  new Paragraph({
                    spacing: { left: 360 },
                    children: [
                      new TextRun({ 
                        text: '"Phone Number", "Type", "Duration (sec)", "Date & Time"',
                        font: "Consolas",
                        size: 20
                      })
                    ]
                  }),
                  new Paragraph({
                    children: [
                      new TextRun({ text: "};", font: "Consolas", size: 20 })
                    ]
                  }),
                  new Paragraph({
                    spacing: { before: 120 },
                    children: [
                      new TextRun({ 
                        text: "// Modify column names or add new columns",
                        font: "Consolas",
                        size: 20,
                        color: "008000"
                      })
                    ]
                  })
                ]
              })
            ]
          })
        ]
      }),

      new Paragraph({
        children: [new PageBreak()]
      }),

      // ========== FOOTER INFO ==========
      new Paragraph({
        heading: HeadingLevel.HEADING_1,
        children: [new TextRun("📝 Additional Information")]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        children: [new TextRun("Color Coding Reference")]
      }),

      // Color reference table
      new Table({
        width: { size: 9360, type: WidthType.DXA },
        columnWidths: [2340, 3510, 3510],
        rows: [
          new TableRow({
            tableHeader: true,
            children: [
              new TableCell({
                width: { size: 2340, type: WidthType.DXA },
                shading: { fill: colors.dark, type: ShadingType.CLEAR },
                margins: { top: 120, bottom: 120, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Call Type", bold: true, color: colors.white, size: 24 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 3510, type: WidthType.DXA },
                shading: { fill: colors.dark, type: ShadingType.CLEAR },
                margins: { top: 120, bottom: 120, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Color", bold: true, color: colors.white, size: 24 })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 3510, type: WidthType.DXA },
                shading: { fill: colors.dark, type: ShadingType.CLEAR },
                margins: { top: 120, bottom: 120, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "RGB Values", bold: true, color: colors.white, size: 24 })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 2340, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "INCOMING", bold: true })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 3510, type: WidthType.DXA },
                shading: { fill: "90EE90", type: ShadingType.CLEAR },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Light Green" })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 3510, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "(144, 238, 144)", font: "Consolas" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 2340, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "OUTGOING", bold: true })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 3510, type: WidthType.DXA },
                shading: { fill: "FFDAB9", type: ShadingType.CLEAR },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Light Orange" })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 3510, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "(255, 218, 185)", font: "Consolas" })
                    ]
                  })
                ]
              })
            ]
          }),
          new TableRow({
            children: [
              new TableCell({
                width: { size: 2340, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "MISSED", bold: true })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 3510, type: WidthType.DXA },
                shading: { fill: "FFB6C1", type: ShadingType.CLEAR },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "Light Red" })
                    ]
                  })
                ]
              }),
              new TableCell({
                width: { size: 3510, type: WidthType.DXA },
                margins: { top: 100, bottom: 100, left: 180, right: 180 },
                children: [
                  new Paragraph({
                    children: [
                      new TextRun({ text: "(255, 182, 193)", font: "Consolas" })
                    ]
                  })
                ]
              })
            ]
          })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 360 },
        children: [new TextRun("Performance Considerations")]
      }),

      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Efficient ArrayList implementation with O(1) random access" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Lazy rendering with JTable for large datasets" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Graphics cache for optimized pie chart rendering" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Event-driven architecture for responsive UI" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Non-blocking user interactions with proper threading" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 360 },
        children: [new TextRun("Known Limitations")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "No persistent storage - all data is session-based (lost on exit)" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "No database integration" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Single-user only - no multi-user support or synchronization" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "No undo/redo functionality" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 360 },
        children: [new TextRun("Future Enhancements")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Database integration (MySQL, PostgreSQL)" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "File-based persistence (JSON, CSV export/import)" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Advanced analytics and reporting features" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "User authentication and multi-user support" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Cloud synchronization capabilities" })
        ]
      }),

      new Paragraph({
        children: [new PageBreak()]
      }),

      // ========== SUMMARY ==========
      new Paragraph({
        heading: HeadingLevel.HEADING_1,
        children: [new TextRun("🎉 Summary")]
      }),

      new Paragraph({
        children: [
          new TextRun({
            text: "The ",
            size: 22
          }),
          new TextRun({
            text: "Call Log Management System",
            size: 22,
            bold: true,
            color: colors.primary
          }),
          new TextRun({
            text: " is a comprehensive example of professional Java Swing development that demonstrates clean architecture, advanced programming concepts, and best practices in software design.",
            size: 22
          })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        children: [new TextRun("Key Takeaways")]
      }),

      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Clean MVC architecture with proper separation of concerns" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Advanced Java concepts including Reflection, Collections, and Graphics2D" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Professional UI/UX design with custom rendering" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Best practices in code organization and documentation" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "checkmarks", level: 0 },
        children: [
          new TextRun({ text: "Event-driven programming and responsive design" })
        ]
      }),

      new Paragraph({
        heading: HeadingLevel.HEADING_2,
        spacing: { before: 360 },
        children: [new TextRun("Perfect For")]
      }),

      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Students learning Java and GUI development" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Understanding enterprise application design patterns" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Building professional desktop applications" })
        ]
      }),
      new Paragraph({
        numbering: { reference: "bullets", level: 0 },
        children: [
          new TextRun({ text: "Learning Swing framework and custom component development" })
        ]
      }),

      // Final info box
      new Paragraph({
        spacing: { before: 720 }
      }),

      new Table({
        width: { size: 9360, type: WidthType.DXA },
        columnWidths: [9360],
        rows: [
          new TableRow({
            children: [
              new TableCell({
                width: { size: 9360, type: WidthType.DXA },
                shading: { fill: colors.primary, type: ShadingType.CLEAR },
                margins: { top: 240, bottom: 240, left: 360, right: 360 },
                children: [
                  new Paragraph({
                    alignment: AlignmentType.CENTER,
                    children: [
                      new TextRun({ 
                        text: "Call Log Management System",
                        size: 32,
                        bold: true,
                        color: colors.white
                      })
                    ]
                  }),
                  new Paragraph({
                    alignment: AlignmentType.CENTER,
                    spacing: { before: 160 },
                    children: [
                      new TextRun({ 
                        text: "Educational Project • 6th Semester Java Course",
                        size: 22,
                        color: colors.white
                      })
                    ]
                  }),
                  new Paragraph({
                    alignment: AlignmentType.CENTER,
                    spacing: { before: 80 },
                    children: [
                      new TextRun({ 
                        text: "Last Updated: May 2026",
                        size: 20,
                        italics: true,
                        color: colors.white
                      })
                    ]
                  }),
                  new Paragraph({
                    alignment: AlignmentType.CENTER,
                    spacing: { before: 160 },
                    children: [
                      new TextRun({ 
                        text: "Java 8+ • Swing Framework • MVC Pattern",
                        size: 22,
                        bold: true,
                        color: colors.white
                      })
                    ]
                  })
                ]
              })
            ]
          })
        ]
      })
    ]
  }]
});

Packer.toBuffer(doc).then(buffer => {
  fs.writeFileSync('/home/claude/call_log_readme.docx', buffer);
  console.log('Document created successfully!');
});
